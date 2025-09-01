package org.wendelsilva.vidaplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wendelsilva.vidaplus.dto.request.PacienteRequestDTO;
import org.wendelsilva.vidaplus.dto.response.PacienteResponseDTO;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.service.PacienteService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(pacienteService.getPacienteById(id));
    }

    @GetMapping("/{id}/consultas")
    public ResponseEntity<Set<Consulta>> getConsultasByPacienteId(@PathVariable UUID id) {
        return ResponseEntity.ok(pacienteService.getConsultasByPacienteId(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO){
        return ResponseEntity.status(201).body(pacienteService.createAndUpdatePaciente(pacienteRequestDTO));
    }

    @PutMapping
    public ResponseEntity<Paciente> updatePaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO) {
        return ResponseEntity.status(201).body(pacienteService.createAndUpdatePaciente(pacienteRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> deletePaciente(@PathVariable UUID id) {
        pacienteService.deletePacienteById(id);
        return ResponseEntity.noContent().build();
    }
}
