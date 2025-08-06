package com.drocsid.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drocsid.model.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {
  List<Message> findByRoomId(UUID id);
}
