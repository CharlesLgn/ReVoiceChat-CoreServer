package fr.revoicechat.core.service.server;

import fr.revoicechat.core.model.Server;
import fr.revoicechat.core.model.ServerUser;
import fr.revoicechat.core.model.User;
import fr.revoicechat.core.repository.UserRepository;
import fr.revoicechat.core.representation.server.NewUserInServer;
import fr.revoicechat.notification.Notification;
import fr.revoicechat.risk.service.server.ServerRoleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServerUserService {

  private final EntityManager entityManager;
  private final ServerRoleService serverRoleService;
  private final UserRepository userRepository;

  public ServerUserService(final EntityManager entityManager, final ServerRoleService serverRoleService, final UserRepository userRepository) {
    this.entityManager = entityManager;
    this.serverRoleService = serverRoleService;
    this.userRepository = userRepository;
  }

  @Transactional
  void join(Server server, User user) {
    ServerUser serverUser = new ServerUser();
    serverUser.setServer(server);
    serverUser.setUser(user);
    entityManager.persist(serverUser);
    if (server.getOwner() == null) {
      server.setOwner(user);
      entityManager.persist(server);
    }
    serverRoleService.addUserToDefaultServerRole(user.getId(), server.getId());
    Notification.of(new NewUserInServer(server.getId(), user.getId())).sendTo(userRepository.findByServers(server.getId()));
  }
}
