package com.drocsid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drocsid.model.Room;
import com.drocsid.repository.RoomRepository;

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
