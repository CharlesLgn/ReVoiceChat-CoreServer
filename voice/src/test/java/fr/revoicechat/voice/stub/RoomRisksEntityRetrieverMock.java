package fr.revoicechat.voice.stub;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;

import fr.revoicechat.risk.technicaldata.RiskEntity;
import fr.revoicechat.voice.service.room.RoomRisksEntityRetriever;

@ApplicationScoped
public class RoomRisksEntityRetrieverMock implements RoomRisksEntityRetriever {
  @Override
  public RiskEntity get(final UUID roomId) {
    return new RiskEntity(UUID.randomUUID(), roomId);
  }
}
