package fr.revoicechat.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.revoicechat.model.User;
import fr.revoicechat.repository.UserRepository;
import fr.revoicechat.representation.user.SignupRepresentation;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(final UserRepository userRepository) {this.userRepository = userRepository;}

  public User create(final SignupRepresentation signer) {
    var user = new User();
    user.setId(UUID.randomUUID());
    user.setCreatedDate(LocalDateTime.now());
    user.setUsername(signer.username());
    user.setLogin(signer.username());
    user.setEmail(signer.email());
    user.setPassword(signer.password());
    return userRepository.save(user);
  }
}
