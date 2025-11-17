package fr.revoicechat.voice.socket.voice;

import java.util.UUID;
import jakarta.websocket.Session;

import fr.revoicechat.voice.risk.LiveDiscussionRisks;
import fr.revoicechat.voice.socket.SessionHolder;

public record VoiceSession(UUID user,
                           UUID room,
                           LiveDiscussionRisks risks,
                           Session session) implements SessionHolder {
  @Override
  public String sessionId() {
    return session.getId();
  }
}
