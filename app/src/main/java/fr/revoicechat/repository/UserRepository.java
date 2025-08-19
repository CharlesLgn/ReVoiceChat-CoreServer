package fr.revoicechat.repository;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.revoicechat.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
  User findByLogin(String login);

  @Query("""
      select u
      from User u
      join u.servers s
      where s.id = :serverID""")
  Stream<User> findByServers(UUID serverID);
}
