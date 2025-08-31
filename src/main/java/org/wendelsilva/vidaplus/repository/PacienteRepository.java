package org.wendelsilva.vidaplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wendelsilva.vidaplus.model.Paciente;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Optional<Paciente> findByEmail(String email);
}
