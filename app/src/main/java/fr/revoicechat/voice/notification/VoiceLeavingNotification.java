package fr.revoicechat.voice.notification;

import java.util.UUID;

import fr.revoicechat.notification.model.NotificationPayload;
import fr.revoicechat.notification.model.NotificationType;

@NotificationType(name = "VOICE_LEAVING")
public class VoiceLeavingNotification implements NotificationPayload {
  private final UUID userId;
  private final UUID roomId;

  public VoiceLeavingNotification(UUID userId, UUID roomId) {
    this.userId = userId;
    this.roomId = roomId;
  }

  @SuppressWarnings("unused") // call by reflection
  public UUID getUserId() {return userId;}

  @SuppressWarnings("unused") // call by reflection
  public UUID getRoomId() {return roomId;}
}
