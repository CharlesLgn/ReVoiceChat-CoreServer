package fr.revoicechat.core.service.serveruser;

import fr.revoicechat.core.model.Server;
import fr.revoicechat.core.model.ServerUser;
import fr.revoicechat.core.model.User;
import fr.revoicechat.core.repository.UserRepository;
import fr.revoicechat.core.representation.server.NewUserInServer;
import fr.revoicechat.notification.Notification;
import fr.revoicechat.risk.service.server.ServerRoleService;
import fr.revoicechat.security.UserHolder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServerUserServiceImpl implements ServerUserService {

  private final EntityManager entityManager;
  private final ServerRoleService serverRoleService;
  private final UserRepository userRepository;
  private final UserHolder userHolder;

  public ServerUserServiceImpl(EntityManager entityManager,
                               ServerRoleService serverRoleService,
                               UserRepository userRepository,
                               UserHolder userHolder) {
    this.entityManager = entityManager;
    this.serverRoleService = serverRoleService;
    this.userRepository = userRepository;
    this.userHolder = userHolder;
  }

  @Override
  @Transactional
  public void join(Server server) {
    User user = userHolder.get();
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
