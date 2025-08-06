package com.drocsid.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drocsid.model.Message;
import com.drocsid.model.Room;

public interface RoomRepository extends JpaRepository<Room, UUID> {}
