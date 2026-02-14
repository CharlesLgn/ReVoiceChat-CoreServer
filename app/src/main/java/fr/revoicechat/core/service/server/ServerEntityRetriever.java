package fr.revoicechat.core.service.server;

import java.util.UUID;

import fr.revoicechat.core.model.Server;

public interface ServerEntityRetriever {

  Server getEntity(final UUID id);
}
