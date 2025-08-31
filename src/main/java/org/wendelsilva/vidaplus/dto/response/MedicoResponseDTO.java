package org.wendelsilva.vidaplus.dto.response;

import lombok.Data;
import org.wendelsilva.vidaplus.model.Especialidade;
import org.wendelsilva.vidaplus.model.Medico;

import java.util.UUID;

@Data
public class MedicoResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;

    public MedicoResponseDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
    }
}
