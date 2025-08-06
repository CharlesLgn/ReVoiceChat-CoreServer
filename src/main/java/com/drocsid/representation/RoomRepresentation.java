package com.drocsid.representation;

import java.time.LocalDateTime;
import java.util.UUID;

import com.drocsid.model.Message;
import com.drocsid.model.Room;

public record RoomRepresentation(String name) {
  public Room toEntity() {
    var room = new Room();
    room.setId(UUID.randomUUID());
    room.setName(name);
    return room;
  }
}
