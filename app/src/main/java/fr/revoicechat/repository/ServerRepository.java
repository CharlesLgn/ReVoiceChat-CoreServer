package fr.revoicechat.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.revoicechat.model.Server;

public interface ServerRepository extends JpaRepository<Server, UUID> {}
