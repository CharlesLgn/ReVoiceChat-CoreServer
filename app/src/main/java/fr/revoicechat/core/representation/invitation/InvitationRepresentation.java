package fr.revoicechat.core.representation.invitation;

import java.util.UUID;

import fr.revoicechat.core.model.InvitationLinkStatus;
import fr.revoicechat.core.model.InvitationType;

public record InvitationRepresentation(UUID id, InvitationLinkStatus status, InvitationType type) {
}
