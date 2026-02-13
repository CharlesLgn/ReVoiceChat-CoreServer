package fr.revoicechat.core.service.server;

import static fr.revoicechat.core.model.InvitationLinkStatus.CREATED;
import static fr.revoicechat.core.model.InvitationType.APPLICATION_JOIN;
import static fr.revoicechat.core.nls.UserErrorCode.USER_WITH_NO_VALID_INVITATION;

import java.util.UUID;

import fr.revoicechat.core.model.InvitationLink;
import fr.revoicechat.core.model.Server;
import fr.revoicechat.core.model.User;
import fr.revoicechat.core.service.invitation.InvitationLinkService;
import fr.revoicechat.security.UserHolder;
import fr.revoicechat.web.error.BadRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * Service responsible for rejoining a {@link Server}.
 */
@ApplicationScoped
public class ServerJoiner {

  private final InvitationLinkService invitationLinkService;
  private final ServerUserService serverUserService;
  private final ServerEntityService serverEntityService;
  private final UserHolder userHolder;

  public ServerJoiner(final InvitationLinkService invitationLinkService, final ServerUserService serverUserService, final ServerEntityService serverEntityService, final UserHolder userHolder) {
    this.invitationLinkService = invitationLinkService;
    this.serverUserService = serverUserService;
    this.serverEntityService = serverEntityService;
    this.userHolder = userHolder;
  }

  @Transactional
  public void join(final UUID serverId, final UUID invitationId) {
    var server = serverEntityService.getEntity(serverId);
    var invitationLink = invitationLinkService.getEntity(invitationId);
    if (!server.isPublic() && !isValideInvitation(serverId, invitationLink)) {
      throw new BadRequestException(USER_WITH_NO_VALID_INVITATION);
    }
    User user = userHolder.get();
    serverUserService.join(server, user);
  }

  private static boolean isValideInvitation(UUID serverId, InvitationLink invitationLink) {
    return invitationLink != null
           && APPLICATION_JOIN.equals(invitationLink.getType())
           && CREATED.equals(invitationLink.getStatus())
           && invitationLink.getTargetedServer().getId().equals(serverId);
  }
}
