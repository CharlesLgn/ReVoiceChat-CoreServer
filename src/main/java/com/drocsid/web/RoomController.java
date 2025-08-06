package com.drocsid.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drocsid.model.Room;
import com.drocsid.representation.RoomRepresentation;
import com.drocsid.service.RoomService;

@RestController
@RequestMapping("room")
public class RoomController {

  private RoomService roomService;

  public RoomController(final RoomService roomService) {
    this.roomService = roomService;
  }

  @GetMapping
  public List<Room> getRooms() {
    return roomService.findAll();
  }

  @PutMapping
  public Room createRoom(@RequestBody RoomRepresentation representation) {
    return roomService.create(representation.toEntity());
  }
}
