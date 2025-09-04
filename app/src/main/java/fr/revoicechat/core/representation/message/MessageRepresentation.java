package fr.revoicechat.core.representation.message;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import fr.revoicechat.notification.model.NotificationPayload;
import fr.revoicechat.notification.model.NotificationType;
import fr.revoicechat.notification.representation.UserNotificationRepresentation;

@NotificationType(name = "ROOM_MESSAGE")
public record MessageRepresentation(
    UUID id,
    String text,
    UUID roomId,
    UserNotificationRepresentation user,
    OffsetDateTime createdDate,
    ActionType actionType,
    List<MediaDataRepresentation> medias
) implements NotificationPayload {

  public enum ActionType {
    ADD, MODIFY, REMOVE
  }
}
