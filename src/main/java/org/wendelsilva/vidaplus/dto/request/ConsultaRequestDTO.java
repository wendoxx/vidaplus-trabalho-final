package org.wendelsilva.vidaplus.dto.request;

import org.wendelsilva.vidaplus.model.ConsultaStatus;
import org.wendelsilva.vidaplus.model.ConsultaTipo;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaRequestDTO (UUID id, UUID pacienteId, UUID medicoId, LocalDateTime dataHora, String motivo, ConsultaStatus status, ConsultaTipo tipo) {

}
