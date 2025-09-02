package org.wendelsilva.vidaplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wendelsilva.vidaplus.dto.request.MedicoRequestDTO;
import org.wendelsilva.vidaplus.dto.response.MedicoResponseDTO;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.service.MedicoService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(medicoService.getMedicoById(id));
    }

    @PutMapping
    public ResponseEntity<Medico> updateMedico(@RequestBody MedicoRequestDTO medicoRequestDTO) {
        return ResponseEntity.status(201).body(medicoService.createAndUpdateMedico(medicoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> deleteMedico(@PathVariable UUID id) {
        medicoService.deleteMedicoById(id);
        return ResponseEntity.noContent().build();
    }

}
