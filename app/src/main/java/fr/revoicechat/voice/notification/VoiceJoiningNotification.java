package fr.revoicechat.voice.notification;

import java.util.UUID;

import fr.revoicechat.notification.model.NotificationPayload;
import fr.revoicechat.notification.model.NotificationType;
import fr.revoicechat.notification.representation.UserNotificationRepresentation;

@NotificationType(name = "VOICE_JOINING")
public class VoiceJoiningNotification implements NotificationPayload {
  private final UserNotificationRepresentation user;
  private final UUID roomId;

  public VoiceJoiningNotification(UserNotificationRepresentation user, UUID roomId) {
    this.user = user;
    this.roomId = roomId;
  }

  @SuppressWarnings("unused") // call by reflection
  public UserNotificationRepresentation getUser() {
    return user;
  }

  @SuppressWarnings("unused") // call by reflection
  public UUID getRoomId() {return roomId;}
}
