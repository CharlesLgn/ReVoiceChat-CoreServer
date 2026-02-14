package fr.revoicechat.core.service.serveruser;

import fr.revoicechat.core.model.Server;

@FunctionalInterface
public interface ServerUserService {
  void join(Server server);
}
