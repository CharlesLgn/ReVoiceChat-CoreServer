package fr.revoicechat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RVC_MESSAGE")
public class Message implements Serializable {
  @Id
  private UUID id;
  private String text;
  private LocalDateTime createdDate;
  @ManyToOne
  @JoinColumn(name="ROOM_ID", nullable=false)
  private Room room;
}
