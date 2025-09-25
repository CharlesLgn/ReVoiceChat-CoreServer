package fr.revoicechat.risk.service.server;

import java.util.List;
import java.util.UUID;

import fr.revoicechat.core.service.ServerService;
import fr.revoicechat.risk.model.Risk;
import fr.revoicechat.risk.model.ServerRoles;
import fr.revoicechat.risk.representation.CreatedServerRoleRepresentation;
import fr.revoicechat.risk.representation.ServerRoleRepresentation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServerRoleService {

  private final EntityManager entityManager;
  private final ServerService serverService;

  public ServerRoleService(final EntityManager entityManager, final ServerService serverService) {
    this.entityManager = entityManager;
    this.serverService = serverService;
  }

  public List<ServerRoleRepresentation> getByServer(UUID serverId) {
    return entityManager.createQuery("select r from ServerRoles r where r.server = :serverId", ServerRoles.class)
                        .setParameter("serverId", serverId)
                        .getResultStream()
                        .map(this::mapToRepresentation)
                        .toList();
  }

  @Transactional
  public ServerRoleRepresentation create(final UUID serverId, final CreatedServerRoleRepresentation representation) {
    serverService.getEntity(serverId);
    ServerRoles roles = new ServerRoles();
    roles.setId(UUID.randomUUID());
    roles.setServer(serverId);
    roles.setPriority(representation.priority());
    roles.setColor(representation.color());
    roles.setName(representation.name());
    entityManager.persist(roles);
    representation.risks().forEach(risk -> {
      var newRisk = new Risk();
      newRisk.setId(UUID.randomUUID());
      newRisk.setEntity(risk.entity());
      newRisk.setMode(risk.mode());
      newRisk.setType(risk.type());
      newRisk.setServerRoles(roles);
      entityManager.persist(newRisk);
    });
    return mapToRepresentation(roles);
  }

  public ServerRoleRepresentation mapToRepresentation(ServerRoles roles) {
    return new ServerRoleRepresentation(
        roles.getId(),
        roles.getName(),
        roles.getColor(),
        roles.getServer()
    );
  }
}
