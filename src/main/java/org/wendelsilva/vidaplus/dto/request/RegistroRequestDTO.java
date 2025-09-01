package org.wendelsilva.vidaplus.dto.request;

import org.wendelsilva.vidaplus.model.Role;

public record RegistroRequestDTO(String email, String nome, String senha, Role role){
}
