package fr.revoicechat.web;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import fr.revoicechat.service.server.ServerProviderService;

public class SignalingHandler extends TextWebSocketHandler {
  private static final Logger LOG = LoggerFactory.getLogger(SignalingHandler.class);
  private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

  @Override
  public void afterConnectionEstablished(@NonNull WebSocketSession session) {
    LOG.info("ConnectionEstablished");
    sessions.add(session);
  }

  @Override
  protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
    LOG.info("handleTextMessage: {}", message);
    for (WebSocketSession s : sessions) {
      if (s.isOpen()) {// && !s.equals(session)) {
        s.sendMessage(message);
      }
    }
  }

  @Override
  public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
    LOG.info("ConnectionClosed");
    sessions.remove(session);
  }
}
