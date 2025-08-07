package fr.revoicechat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RVC_USER")
public class User implements Serializable {
  @Id
  private UUID id;
  @Column(unique = true)
  private String email;
  private String login;
  private String password;
  private LocalDateTime createdDate;
  @ManyToMany
  @JoinTable(name = "RVC_SERVER_USER",
      joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "SERVER_ID", referencedColumnName = "ID"))
  private List<Server> servers;
}
