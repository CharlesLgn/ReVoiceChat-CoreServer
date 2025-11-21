package fr.revoicechat.core.web;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.revoicechat.core.representation.login.UserPassword;
import fr.revoicechat.core.representation.user.SignupRepresentation;
import fr.revoicechat.core.representation.user.UserRepresentation;
import fr.revoicechat.core.service.UserService;
import fr.revoicechat.security.service.SecurityTokenService;
import fr.revoicechat.security.utils.PasswordUtils;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Authentication", description = "Handle user authentication, registration, and session management")
public class AuthController {

  private final UserService userService;
  private final SecurityIdentity securityIdentity;
  private final SecurityTokenService securityTokenService;

  public AuthController(UserService userService,
                        SecurityIdentity securityIdentity,
                        SecurityTokenService securityTokenService) {
    this.userService = userService;
    this.securityIdentity = securityIdentity;
    this.securityTokenService = securityTokenService;
  }

  @Operation(
      summary = "Register new user",
      description = "Create a new user account with the provided registration details. Upon successful registration, the user can log in using their credentials."
  )
  @RequestBody(
      description = "User registration information including username, password, and optional profile details",
      content = @Content(schema = @Schema(implementation = SignupRepresentation.class))
  )
  @APIResponse(
      responseCode = "200",
      description = "User account created successfully",
      content = @Content(schema = @Schema(implementation = UserRepresentation.class))
  )
  @APIResponse(
      responseCode = "400",
      description = "Invalid registration data or username already exists",
      content = @Content(
          mediaType = "text/plain",
          schema = @Schema(implementation = String.class, examples = "Invalid input data")
      )
  )
  @APIResponse(
      responseCode = "409",
      description = "Username or email already in use",
      content = @Content(
          mediaType = "text/plain",
          schema = @Schema(implementation = String.class, examples = "Username already exists")
      )
  )
  @PUT
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/signup")
  public UserRepresentation signup(SignupRepresentation user) {
    return userService.create(user);
  }

  @Operation(
      summary = "User login",
      description = "Authenticate a user with their username and password. Returns a JWT token that must be included in the Authorization header for subsequent authenticated requests."
  )
  @RequestBody(
      description = "User credentials consisting of username (or display name) and password",
      content = @Content(schema = @Schema(implementation = UserPassword.class))
  )
  @APIResponse(
      responseCode = "200",
      description = "Authentication successful, JWT token returned",
      content = @Content(
          mediaType = "text/plain",
          schema = @Schema(implementation = String.class, examples = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
      )
  )
  @APIResponse(
      responseCode = "401",
      description = "Authentication failed due to invalid credentials",
      content = @Content(
          mediaType = "text/plain",
          schema = @Schema(implementation = String.class, examples = "Invalid credentials")
      )
  )
  @POST
  @PermitAll
  @Path("/login")
  @Produces(MediaType.TEXT_PLAIN)
  public Response login(UserPassword request) {
    var user = userService.findByLogin(request.username());
    if (user != null && PasswordUtils.matches(request.password(), user.getPassword())) {
      return Response.ok(securityTokenService.generate(user)).build();
    } else {
      return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
    }
  }

  @Operation(
      summary = "User logout",
      description = "Invalidate the current user session by blacklisting the JWT token. The token will no longer be valid for authentication after this operation."
  )
  @APIResponse(
      responseCode = "200",
      description = "User logged out successfully and token blacklisted"
  )
  @APIResponse(
      responseCode = "204",
      description = "No active session to log out"
  )
  @GET
  @PermitAll
  @Path("/logout")
  @Produces(MediaType.TEXT_PLAIN)
  public Response logout() {
    if (securityIdentity.getPrincipal() instanceof JsonWebToken jsonWebToken) {
      securityTokenService.blackList(jsonWebToken);
      return Response.ok().build();
    } else {
      return Response.status(Status.NO_CONTENT).build();
    }
  }
}
