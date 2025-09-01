package org.wendelsilva.vidaplus.dto.request;

import org.wendelsilva.vidaplus.model.Especialidade;

import java.util.UUID;

public record MedicoRequestDTO(UUID id, String nome, Especialidade especialidade, String crm, String telefone, String email, String senha) {
}
