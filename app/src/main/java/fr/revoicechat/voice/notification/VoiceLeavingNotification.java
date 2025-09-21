package fr.revoicechat.voice.notification;

import java.util.UUID;

import fr.revoicechat.notification.model.NotificationPayload;
import fr.revoicechat.notification.model.NotificationType;

@NotificationType(name = "VOICE_LEAVING")
public record VoiceLeavingNotification(UUID userId, UUID roomId) implements NotificationPayload {}
