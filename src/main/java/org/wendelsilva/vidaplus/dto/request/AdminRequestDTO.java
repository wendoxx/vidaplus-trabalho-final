package org.wendelsilva.vidaplus.dto.request;

import java.util.UUID;

public record AdminRequestDTO(UUID id, String nome, String email, String senha) {
}
