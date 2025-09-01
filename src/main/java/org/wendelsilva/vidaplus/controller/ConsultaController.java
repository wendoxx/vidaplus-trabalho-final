package org.wendelsilva.vidaplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wendelsilva.vidaplus.dto.request.ConsultaRequestDTO;
import org.wendelsilva.vidaplus.dto.response.ConsultaResponseDTO;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.service.ConsultaService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(consultaService.getConsultaById(id));
    }

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@RequestBody ConsultaRequestDTO consultaResponseDTO) {
        return ResponseEntity.status(201).body(consultaService.createAndUpdateConsulta(consultaResponseDTO));
    }

    @PutMapping
    public ResponseEntity<Consulta> updateConsulta(@RequestBody ConsultaRequestDTO consultaRequestDTO) {
        return ResponseEntity.status(201).body(consultaService.createAndUpdateConsulta(consultaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> deleteConsulta(@PathVariable UUID id) {
        consultaService.deleteConsultaById(id);
        return ResponseEntity.noContent().build();
    }
}
