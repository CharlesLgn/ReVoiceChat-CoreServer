package fr.revoicechat.core.dev;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.revoicechat.core.model.UserType;
import fr.revoicechat.core.repository.UserRepository;
import fr.revoicechat.core.service.ServerService;
import io.quarkus.runtime.Startup;

@ApplicationScoped
@Startup
public class GenerateFictiveUsers {
  private static final Logger LOG = LoggerFactory.getLogger(GenerateFictiveUsers.class);

  private final UserRepository userRepository;
  private final UserCreator userCreator;
  private final ServerService serverService;

  @ConfigProperty(name = "revoicechat.test.generate-fictive-users", defaultValue = "false")
  boolean canBeCalled;

  GenerateFictiveUsers(UserRepository userRepository, final UserCreator userCreator, ServerService serverService) {
    LOG.info("GenerateFictiveUsers built");
    this.userRepository = userRepository;
    this.userCreator = userCreator;
    this.serverService = serverService;
  }

  @PostConstruct
  public void init() {
    if (canBeCalled) {
      if (userRepository.count() == 0) {
        LOG.info("default admin user generated");
        var admin = userCreator.add("admin", "The admin", "--no-email--", UserType.ADMIN);
        var user = userCreator.add("user", "The user", "-no-email-", UserType.USER);
        serverService.joinDefaultServer(admin);
        serverService.joinDefaultServer(user);
      } else {
        LOG.info("db has already user in it");
      }
    }
  }
}
