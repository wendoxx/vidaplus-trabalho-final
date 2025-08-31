package org.wendelsilva.vidaplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wendelsilva.vidaplus.model.Administrador;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
    Optional<Administrador> findByEmail(String email);
}
