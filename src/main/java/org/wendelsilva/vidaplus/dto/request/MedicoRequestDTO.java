package org.wendelsilva.vidaplus.dto.request;

import java.util.UUID;

public record MedicoRequestDTO(UUID id, String nome, String especialidade, String crm, String telefone, String email, String senha) {
}
