package org.wendelsilva.vidaplus.dto.response;

import lombok.Data;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.model.ConsultaTipo;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ConsultaResponseDTO {
    private UUID id;
    private UUID paciente;
    private UUID medico;
    private LocalDateTime dataHora;
    private String motivo;
    private String status;
    private ConsultaTipo tipo;

    public ConsultaResponseDTO (Consulta consulta) {
        this.id = consulta.getId();
        this.paciente = consulta.getPaciente().getId();
        this.medico = consulta.getMedico().getId();
        this.dataHora = consulta.getDataHora();
        this.motivo = consulta.getMotivo();
        this.status = consulta.getStatus().name();
        this.tipo = consulta.getTipo();
    }

}
