package fr.revoicechat.representation.room;

import fr.revoicechat.model.RoomType;

public record RoomRepresentation(
    String name,
    RoomType type
) {}
