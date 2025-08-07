package fr.revoicechat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.revoicechat.model.Room;
import fr.revoicechat.repository.RoomRepository;

@Service
public class RoomService {

  private final RoomRepository repository;

  public RoomService(final RoomRepository repository) {
    this.repository = repository;
  }

  public List<Room> findAll() {
    return repository.findAll();
  }

  public Room create(final Room room) {
    return repository.save(room);
  }
}
