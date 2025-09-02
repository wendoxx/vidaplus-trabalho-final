package org.wendelsilva.vidaplus.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.PacienteRequestDTO;
import org.wendelsilva.vidaplus.dto.response.PacienteResponseDTO;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.repository.PacienteRepository;

import java.util.Set;
import java.util.UUID;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Paciente createAndUpdatePaciente(PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente;
        if (pacienteRequestDTO.id() != null && pacienteRepository.existsById(pacienteRequestDTO.id())) {
            paciente = pacienteRepository.findById(pacienteRequestDTO.id()).get();
        } else {
            paciente = new Paciente();
        }
        String senhaCriptografada = passwordEncoder.encode(pacienteRequestDTO.senha());
        paciente.setNome(pacienteRequestDTO.nome());
        paciente.setDataNascimento(pacienteRequestDTO.dataNascimento());
        paciente.setCpf(pacienteRequestDTO.cpf());
        paciente.setTelefone(pacienteRequestDTO.telefone());
        paciente.setEmail(pacienteRequestDTO.email());
        paciente.setSenha(senhaCriptografada);
        paciente.setEndereco(pacienteRequestDTO.endereco());
        return pacienteRepository.save(paciente);
    }

    public PacienteResponseDTO getPacienteById(UUID id) {
        return pacienteRepository.findById(id).map(PacienteResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
    }

    public Set<Consulta> getConsultasByPacienteId(UUID pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado."));
        return paciente.getConsultas();
    }

    public void deletePacienteById(UUID id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente não encontrado.");
        }
        pacienteRepository.deleteById(id);
    }
}