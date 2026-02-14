package fr.revoicechat.core.service.server;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import fr.revoicechat.core.model.Server;
import fr.revoicechat.core.model.User;

/**
 * Defines the contract for services that provide access to available {@link Server} instances.
 * <p>
 * Implementations determine whether the application is running in a mode that supports their
 * behavior and can return the list of servers accordingly.
 * @see MultiServerProviderService
 */
public interface ServerProviderService {

  /**
   * Checks whether this provider mode can be used in the current application state.
   * <p>
   * Implementations may throw an exception if the mode is incompatible with the existing server configuration.
   *
   * @throws IllegalStateException if the mode cannot be used
   */
  List<Server> getServers();

  Stream<User> getUsers(UUID id);

  /**
   * Check if we can create a server, and create it.
   */
  void create(Server entity);

  /**
   * Check if we can delete a server, and delete it.
   */
  void delete(UUID id);
}
