package fr.revoicechat.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.revoicechat.model.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {
  List<Message> findByRoomId(UUID id);
}
