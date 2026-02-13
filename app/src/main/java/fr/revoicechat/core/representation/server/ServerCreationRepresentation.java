package fr.revoicechat.core.representation.server;

import fr.revoicechat.core.model.ServerType;

public record ServerCreationRepresentation(String name, ServerType serverType) {}
