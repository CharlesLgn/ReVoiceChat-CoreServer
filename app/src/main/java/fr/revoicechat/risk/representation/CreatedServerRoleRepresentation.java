package fr.revoicechat.risk.representation;

import java.util.List;

public record CreatedServerRoleRepresentation(
    String name,
    String color,
    int priority,
    List<RiskRepresentation> risks
) {}
