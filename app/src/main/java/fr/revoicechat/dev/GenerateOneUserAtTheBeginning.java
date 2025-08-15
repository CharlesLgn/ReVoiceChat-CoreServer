package fr.revoicechat.dev;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.revoicechat.model.User;
import fr.revoicechat.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Profile("dev")
class GenerateOneUserAtTheBeginning {
  private static final Logger LOG = LoggerFactory.getLogger(GenerateOneUserAtTheBeginning.class);

  private final UserRepository userRepository;

  public GenerateOneUserAtTheBeginning(final UserRepository userRepository) {this.userRepository = userRepository;}

  @PostConstruct
  @Transactional
  public void init() {
    if (userRepository.count() < 1) {
      LOG.info("default admin user generated");
      var user = new User();
      user.setId(UUID.randomUUID());
      user.setLogin("admin");
      user.setUsername("admin");
      user.setPassword("psw");
      user.setEmail("--no-email--");
      user.setCreatedDate(LocalDateTime.now());
      userRepository.save(user);
    }
  }
}
