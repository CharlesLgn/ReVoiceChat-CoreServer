package fr.revoicechat.voice.socket.stream;

import java.util.UUID;

import fr.revoicechat.voice.risk.LiveDiscussionRisks;
import fr.revoicechat.voice.socket.SessionHolder;

public interface StreamSession extends SessionHolder {
  UUID user();
  LiveDiscussionRisks risks();
}
