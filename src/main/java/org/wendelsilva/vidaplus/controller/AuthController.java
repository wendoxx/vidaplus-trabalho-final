package org.wendelsilva.vidaplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wendelsilva.vidaplus.dto.request.AdminRequestDTO;
import org.wendelsilva.vidaplus.dto.request.LoginRequestDTO;
import org.wendelsilva.vidaplus.dto.request.MedicoRequestDTO;
import org.wendelsilva.vidaplus.dto.request.PacienteRequestDTO;
import org.wendelsilva.vidaplus.dto.response.LoginResponseDTO;
import org.wendelsilva.vidaplus.model.Admin;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.service.AdminService;
import org.wendelsilva.vidaplus.service.AuthService;
import org.wendelsilva.vidaplus.service.MedicoService;
import org.wendelsilva.vidaplus.service.PacienteService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    MedicoService medicoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/registro-medico")
    public ResponseEntity<Medico> registrarMedico(@RequestBody MedicoRequestDTO medicoRequestDTO) {
        return ResponseEntity.status(201).body(medicoService.createAndUpdateMedico(medicoRequestDTO));
    }

    @PostMapping("/registro-paciente")
    public ResponseEntity<Paciente> registerPaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO) {
        return ResponseEntity.status(201).body(pacienteService.createAndUpdatePaciente(pacienteRequestDTO));
    }

    @PostMapping("/registro-admin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.status(201).body(adminService.createAndUpdateAdmin(adminRequestDTO));
    }

    @PutMapping("/update-admin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.status(201).body(adminService.createAndUpdateAdmin(adminRequestDTO));
    }
}
