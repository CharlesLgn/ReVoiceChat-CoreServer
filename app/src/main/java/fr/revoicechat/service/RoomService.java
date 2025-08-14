package fr.revoicechat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.revoicechat.model.Room;
import fr.revoicechat.repository.RoomRepository;

/**
 * Service responsible for managing {@link Room} entities.
 * <p>
 * This service acts as a bridge between the application layer and the persistence layer,
 * providing methods to:
 * <ul>
 *     <li>Retrieve a list of all room</li>
 *     <li>Retrieve a single room by its ID</li>
 *     <li>Create new room</li>
 * </ul>
 * <p>
 */
@Service
public class RoomService {

  private final RoomRepository repository;
  private final ServerService serverService;

  public RoomService(final RoomRepository repository, final ServerService serverService) {
    this.repository = repository;
    this.serverService = serverService;
  }

  /**
   * Retrieves all available rooms of a server.
   *
   * @return a list of available rooms, possibly empty
   */
  public List<Room> findAll(final UUID id) {
    return repository.findByServerId(id);
  }

  /**
   * Creates and stores a new room in the database.
   *
   * @param id the server id
   * @param room the room entity to persist
   * @return the persisted room entity with its generated ID
   * @throws java.util.NoSuchElementException if no server with the given ID exists
   */
  public Room create(final UUID id, final Room room) {
    var server = serverService.get(id);
    room.setServer(server);
    return repository.save(room);
  }

  /**
   * Retrieves a room from the database by its unique identifier.
   *
   * @param roomId the unique room ID
   * @return the room entity
   * @throws java.util.NoSuchElementException if no room with the given ID exists
   */
  public Room get(final UUID roomId) {
    return repository.findById(roomId).orElseThrow();
  }
}
