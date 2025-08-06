package com.drocsid.service;

import static java.util.Collections.synchronizedSet;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.drocsid.model.Message;
import com.drocsid.repository.MessageRepository;
import com.drocsid.repository.RoomRepository;
import jakarta.transaction.Transactional;

@Service
public class TextualChatService {
  private static final Logger LOG = LoggerFactory.getLogger(TextualChatService.class);

  private final Map<UUID, Collection<SseEmitter>> emitters = new ConcurrentHashMap<>();

  private final MessageRepository messageRepository;
  private final RoomRepository roomRepository;

  public TextualChatService(final MessageRepository messageRepository, final RoomRepository roomRepository) {
    this.messageRepository = messageRepository;
    this.roomRepository = roomRepository;
  }

  public List<Message> findAllMessage(final UUID roomId) {
    return messageRepository.findByRoomId(roomId);
  }

  public SseEmitter register(final UUID roomId) {
    SseEmitter emitter = new SseEmitter(0L);
    emitter.onCompletion(() -> remove(roomId, emitter, () -> LOG.info("complete")));
    emitter.onError(e -> remove(roomId, emitter, () -> LOG.error("error", e)));
    emitter.onTimeout(() -> remove(roomId, emitter, () -> LOG.error("timeout")));
    getSseEmitters(roomId).add(emitter);
    return emitter;
  }

  @Transactional
  public void send(final UUID roomId, Message message) {
    var room = roomRepository.findById(roomId).orElseThrow();
    message.setRoom(room);
    messageRepository.save(message);
    getSseEmitters(roomId).forEach(sse -> sendSSE(sse, message));
  }

  private Collection<SseEmitter> getSseEmitters(final UUID roomId) {
    return emitters.computeIfAbsent(roomId, key -> synchronizedSet(new HashSet<>()));
  }

  private void sendSSE(final SseEmitter sse, final Message message) {
    try {
      sse.send(message);
    } catch (IOException e) {
      sse.completeWithError(e);
    }
  }

  private void remove(final UUID roomId, SseEmitter emitter, Runnable log) {
    getSseEmitters(roomId).remove(emitter);
    log.run();
  }
}
