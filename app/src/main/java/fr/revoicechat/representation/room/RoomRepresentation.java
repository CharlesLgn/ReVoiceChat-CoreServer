package fr.revoicechat.representation.room;

import java.util.UUID;

import fr.revoicechat.model.Room;

public record RoomRepresentation(String name) {
  public Room toEntity() {
    var room = new Room();
    room.setId(UUID.randomUUID());
    room.setName(name);
    return room;
  }
}
