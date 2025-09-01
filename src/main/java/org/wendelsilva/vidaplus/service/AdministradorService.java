package org.wendelsilva.vidaplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.AdministradorRequestDTO;
import org.wendelsilva.vidaplus.dto.response.AdministradorResponseDTO;
import org.wendelsilva.vidaplus.model.Administrador;
import org.wendelsilva.vidaplus.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador createAndUpdateAdministrador(AdministradorRequestDTO administradorRequestDTO) {
        Administrador administrador;
        if (administradorRequestDTO.id() != null && administradorRepository.existsById(administradorRequestDTO.id())) {
            administrador = administradorRepository.findById(administradorRequestDTO.id()).get();
        } else {
            administrador = new Administrador();
        }
        administrador.setNome(administradorRequestDTO.nome());
        administrador.setEmail(administradorRequestDTO.email());
        administrador.setTelefone(administradorRequestDTO.telefone());
        administrador.setSenha(administradorRequestDTO.senha());
        return administradorRepository.save(administrador);
    }

    public AdministradorResponseDTO getAdministradorById(AdministradorRequestDTO administradorRequestDTO) {
        return administradorRepository.findById(administradorRequestDTO.id()).map( AdministradorResponseDTO::new).orElseThrow( () -> new RuntimeException("Administrador not found"));
    }

    public void deleteAdministradorById(java.util.UUID id) {
        administradorRepository.deleteById(id);
    }
}
