package fr.revoicechat.risk.representation;

import java.util.UUID;

public record ServerRoleRepresentation(
    UUID id,
    String name,
    String color,
    UUID serverId
) {
}
