package fr.revoicechat.repository;

import java.util.List;

import fr.revoicechat.model.Server;

public interface ServerRepository {
  long count();
  List<Server> findAll();
}
