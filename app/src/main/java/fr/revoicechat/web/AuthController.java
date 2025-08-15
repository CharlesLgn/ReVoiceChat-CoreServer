package fr.revoicechat.web;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.revoicechat.model.User;
import fr.revoicechat.representation.user.SignupRepresentation;
import fr.revoicechat.service.UserService;

@RestController
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public AuthController(AuthenticationManager authenticationManager, final UserService userService) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @PutMapping("/signup")
  public User signup(@RequestBody SignupRepresentation user) {
    return userService.create(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody UserPassword user) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(), user.password()));
    return "User " + user.username() + " logged in";
  }

  public record UserPassword(String username, String password) {}
}
