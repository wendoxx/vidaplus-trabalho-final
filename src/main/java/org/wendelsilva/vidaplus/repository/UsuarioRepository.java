package org.wendelsilva.vidaplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wendelsilva.vidaplus.model.Usuario;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
