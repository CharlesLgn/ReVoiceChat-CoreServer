package fr.revoicechat.voice.stub;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;

import fr.revoicechat.risk.service.server.ServerFinder;

@ApplicationScoped
public class ServerFinderMock implements ServerFinder {
  @Override
  public void existsOrThrow(final UUID id) {
    // nothing here
  }
}
