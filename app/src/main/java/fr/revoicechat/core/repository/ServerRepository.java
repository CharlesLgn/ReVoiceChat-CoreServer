package fr.revoicechat.core.repository;

import java.util.List;
import java.util.stream.Stream;

import fr.revoicechat.core.model.Server;
import fr.revoicechat.core.model.User;

public interface ServerRepository {
  long count();
  List<Server> findAll();
  Stream<Server> getByUser(User user);

  Stream<Server> getPublicServer();
}
