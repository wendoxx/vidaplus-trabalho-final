package org.wendelsilva.vidaplus.dto.request;

import java.util.UUID;

public record ConsultaRequestDTO (UUID id, UUID pacienteId, UUID medicoId, String dataHora, String motivo, String status) {

}
