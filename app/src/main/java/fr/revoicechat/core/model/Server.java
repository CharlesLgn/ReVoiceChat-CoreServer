package fr.revoicechat.core.model;

import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import fr.revoicechat.core.model.server.ServerStructure;
import fr.revoicechat.core.repository.jpa.ServerStructureConverter;

@Entity
@Table(name = "RVC_SERVER")
public class Server {
  @Id
  private UUID id;
  private String name;
  /** User can be nullable in case e are in mono server. */
  @ManyToOne
  @JoinColumn(name="OWNER_ID")
  private User owner;
  @Convert(converter = ServerStructureConverter.class)
  @Column(columnDefinition = "TEXT")
  private ServerStructure structure;

  public Server() {
    super();
  }

  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(final User owner) {
    this.owner = owner;
  }

  public ServerStructure getStructure() {
    return structure;
  }

  public void setStructure(final ServerStructure structure) {
    this.structure = structure;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) { return true; }
    if (!(o instanceof Server server)) { return false; }
    return Objects.equals(getId(), server.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
