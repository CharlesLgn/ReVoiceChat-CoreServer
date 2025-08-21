package fr.revoicechat.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import fr.revoicechat.model.User;

public interface UserRepository {
  User findByLogin(String login);
  Stream<User> findByServers(UUID serverID);
  long count();
  List<User> findAll();
}
