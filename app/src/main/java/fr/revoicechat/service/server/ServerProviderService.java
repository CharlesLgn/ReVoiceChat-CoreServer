package fr.revoicechat.service.server;

import java.util.List;

import fr.revoicechat.model.Server;

public interface ServerProviderService {

  void canBeUsed();

  List<Server> getServers();
}
