package org.wendelsilva.vidaplus.dto.response;

import lombok.Data;
import org.wendelsilva.vidaplus.model.Paciente;

import java.util.Date;
import java.util.UUID;

@Data
public class PacienteResponseDTO {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Date dataNascimento;
    private String endereco;

    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.cpf = paciente.getCpf();
        this.dataNascimento = paciente.getDataNascimento();
        this.endereco = paciente.getEndereco();
    }
}
