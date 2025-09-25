package fr.revoicechat.risk.representation;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.revoicechat.risk.model.RiskMode;
import fr.revoicechat.risk.service.risk.RiskTypeDeserializer;
import fr.revoicechat.risk.service.risk.RiskTypeSerializer;
import fr.revoicechat.risk.type.RiskType;

public record RiskRepresentation(
    @JsonDeserialize(using = RiskTypeDeserializer.class)
    @JsonSerialize(using = RiskTypeSerializer.class)
    RiskType type,
    UUID entity,
    RiskMode mode
) {}
