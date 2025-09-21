package fr.revoicechat.risk.service;

import java.util.List;
import java.util.UUID;

import fr.revoicechat.risk.technicaldata.RiskEntity;
import fr.revoicechat.risk.model.UserRoleMembership;
import fr.revoicechat.risk.model.ServerRoles;
import fr.revoicechat.risk.type.RiskType;
import fr.revoicechat.security.UserHolder;
import io.quarkus.arc.Unremovable;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;

@Singleton
@Unremovable
class RiskServiceImpl implements RiskService {

  private final EntityManager entityManager;
  private final UserHolder userHolder;

  public RiskServiceImpl(final EntityManager entityManager, final UserHolder userHolder) {
    this.entityManager = entityManager;
    this.userHolder = userHolder;
  }

  @Override
  public boolean hasRisk(final RiskEntity entity, final RiskType riskType) {
    return hasRisk(userHolder.get().getId(), entity, riskType);
  }

  @Override
  public boolean hasRisk(final UUID userId, final RiskEntity entity, final RiskType riskType) {
    var serverRoles = getServerRoles(userId);
    if (serverRoles.isEmpty()) {
      // no server roles = everybody has full habilitation
      return true;
    }
    var roles = getRoles(entity, riskType);
    if (roles.isEmpty()) {
      // no distinct roles = everybody has the habilitations
      return true;
    }
    var user = entityManager.getReference(UserRoleMembership.class, userId);
    return user.getServerRoles().stream().anyMatch(roles::contains);
  }

  private List<ServerRoles> getServerRoles(final UUID userId) {
    return entityManager.createQuery("""
                            select u
                            from ServerRoles u
                            where u.server = :id""", ServerRoles.class)
                        .setParameter("id", userId)
                        .getResultList();
  }

  private List<ServerRoles> getRoles(final RiskEntity entity, final RiskType riskType) {
    return entityManager.createQuery("""
                            select r
                            from Role r
                            where r.riskType = :riskType
                              and r.serverRoles.server = :serverId
                              and (r.entity = null or r.entity = :entityId)
                            """, ServerRoles.class)
                        .setParameter("riskType", riskType)
                        .setParameter("serverId", entity.serverId())
                        .setParameter("entityId", entity.entityId())
                        .getResultList();
  }
}
