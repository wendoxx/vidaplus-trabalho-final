package org.wendelsilva.vidaplus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.wendelsilva.vidaplus.model.Role;

@Data
@AllArgsConstructor
public class RegistroResponseDTO {
    private String email;
    private Role role;
}
