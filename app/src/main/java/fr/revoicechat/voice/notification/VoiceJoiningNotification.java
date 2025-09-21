package fr.revoicechat.voice.notification;

import java.util.UUID;

import fr.revoicechat.notification.model.NotificationPayload;
import fr.revoicechat.notification.model.NotificationType;
import fr.revoicechat.notification.representation.UserNotificationRepresentation;

@NotificationType(name = "VOICE_JOINING")
public record VoiceJoiningNotification(UserNotificationRepresentation user, UUID roomId) implements NotificationPayload {}
