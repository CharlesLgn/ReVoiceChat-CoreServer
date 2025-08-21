package fr.revoicechat.repository;

import java.util.List;
import java.util.UUID;

import fr.revoicechat.model.Room;

public interface RoomRepository {
  List<Room> findByServerId(UUID serverId);
}
