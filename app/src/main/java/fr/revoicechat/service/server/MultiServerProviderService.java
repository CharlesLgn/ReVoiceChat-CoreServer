package fr.revoicechat.service.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.revoicechat.model.Server;
import fr.revoicechat.repository.ServerRepository;

@Service
public class MultiServerProviderService implements ServerProviderService {
  private static final Logger LOG = LoggerFactory.getLogger(ServerProviderService.class);

  private final ServerRepository serverRepository;

  public MultiServerProviderService(final ServerRepository serverRepository) {this.serverRepository = serverRepository;}

  @Override
  public void canBeUsed() {
    LOG.info("Multi server mode : can be used");
  }

  @Override
  public List<Server> getServers() {
    return serverRepository.findAll();
  }
}
