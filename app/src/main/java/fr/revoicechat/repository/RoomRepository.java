package fr.revoicechat.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.revoicechat.model.Room;

public interface RoomRepository extends JpaRepository<Room, UUID> {
  List<Room> findByServerId(UUID serverId);
}
