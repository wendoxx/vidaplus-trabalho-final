package org.wendelsilva.vidaplus.dto.request;

import java.util.UUID;

public record AdministradorRequestDTO(UUID id, String nome, String email, String senha, String telefone) {
}
