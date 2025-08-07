package fr.revoicechat.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RVC_SERVER")
public class Server implements Serializable {
  @Id
  private UUID id;
  private String name;
  @ManyToOne
  @JoinColumn(name="OWNER_ID", nullable=false)
  private User owner;
}
