package fr.revoicechat.core.dev;

import java.time.OffsetDateTime;
import java.util.UUID;

import fr.revoicechat.core.model.User;
import fr.revoicechat.core.model.UserType;
import fr.revoicechat.security.utils.PasswordUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserCreator {

  private final EntityManager entityManager;

  UserCreator(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public User add(String login, String displayName, String mail, UserType type) {
    var user = new User();
    user.setId(UUID.randomUUID());
    user.setLogin(login);
    user.setDisplayName(displayName);
    user.setPassword(PasswordUtils.encodePassword("psw"));
    user.setEmail(mail);
    user.setCreatedDate(OffsetDateTime.now());
    user.setType(type);
    entityManager.persist(user);
    return user;
  }
}