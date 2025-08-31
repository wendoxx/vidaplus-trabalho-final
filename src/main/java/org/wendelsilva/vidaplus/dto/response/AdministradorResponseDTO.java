package org.wendelsilva.vidaplus.dto.response;

import org.wendelsilva.vidaplus.model.Administrador;

import java.util.UUID;

public class AdministradorResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;

    public AdministradorResponseDTO(Administrador administrador) {
        this.id = administrador.getId();
        this.nome = administrador.getNome();
        this.email = administrador.getEmail();
        this.telefone = administrador.getTelefone();
    }
}