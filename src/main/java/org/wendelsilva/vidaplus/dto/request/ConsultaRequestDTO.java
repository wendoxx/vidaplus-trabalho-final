package org.wendelsilva.vidaplus.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaRequestDTO (UUID id, UUID pacienteId, UUID medicoId, LocalDateTime dataHora, String motivo, String status) {

}
