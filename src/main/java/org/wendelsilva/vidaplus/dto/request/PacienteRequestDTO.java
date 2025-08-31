package org.wendelsilva.vidaplus.dto.request;

import java.util.Date;
import java.util.UUID;

public record PacienteRequestDTO(UUID id, String nome, Date dataNascimento, String cpf, String telefone, String email, String senha, String endereco) {
}
