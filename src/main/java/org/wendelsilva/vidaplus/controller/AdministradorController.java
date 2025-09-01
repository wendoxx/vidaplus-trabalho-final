package org.wendelsilva.vidaplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wendelsilva.vidaplus.dto.request.AdministradorRequestDTO;
import org.wendelsilva.vidaplus.dto.response.AdministradorResponseDTO;
import org.wendelsilva.vidaplus.model.Administrador;
import org.wendelsilva.vidaplus.service.AdministradorService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> findById(UUID id) {
        return ResponseEntity.ok(administradorService.getAdministradorById(id));
    }

    @PostMapping
    public ResponseEntity<Administrador> createAdministrador(AdministradorRequestDTO administradorRequestDTO) {
        return ResponseEntity.status(201).body(administradorService.createAndUpdateAdministrador(administradorRequestDTO));
    }

    @PutMapping
    public ResponseEntity<Administrador> updateAdministrador(AdministradorRequestDTO administradorRequestDTO) {
        return ResponseEntity.status(201).body(administradorService.createAndUpdateAdministrador(administradorRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> deleteAdministrador(UUID id) {
        administradorService.deleteAdministradorById(id);
        return ResponseEntity.noContent().build();
    }
}
