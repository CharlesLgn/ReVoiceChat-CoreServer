package fr.revoicechat.repository;

import java.util.UUID;

import fr.revoicechat.model.Message;
import fr.revoicechat.repository.page.PageResult;

public interface MessageRepository {
  PageResult<Message> findByRoomId(UUID roomId, int page, int size);
}
