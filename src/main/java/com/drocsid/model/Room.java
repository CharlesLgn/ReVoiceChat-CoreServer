package com.drocsid.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "DROCSID_ROOM")
public class Room implements Serializable {
  @Id
  private UUID id;
  private String name;
}
