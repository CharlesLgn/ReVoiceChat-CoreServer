package fr.revoicechat.voice.service.risk;

import java.util.UUID;

import fr.revoicechat.risk.technicaldata.RiskEntity;

public interface RoomRisksEntityRetriever {

  RiskEntity get(UUID roomId);
}
