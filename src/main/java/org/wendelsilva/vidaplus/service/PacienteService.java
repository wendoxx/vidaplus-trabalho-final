package org.wendelsilva.vidaplus.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.PacienteRequestDTO;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente createAndUpdatePaciente(PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente;
        if (pacienteRequestDTO.id() != null && pacienteRepository.existsById(pacienteRequestDTO.id())) {
            paciente = pacienteRepository.findById(pacienteRequestDTO.id()).get();
        } else {
            paciente = new Paciente();
        }
        paciente.setNome(pacienteRequestDTO.nome());
        paciente.setDataNascimento(pacienteRequestDTO.dataNascimento());
        paciente.setCpf(pacienteRequestDTO.cpf());
        paciente.setTelefone(pacienteRequestDTO.telefone());
        paciente.setEmail(pacienteRequestDTO.email());
        paciente.setSenha(pacienteRequestDTO.senha());
        paciente.setEndereco(pacienteRequestDTO.endereco());
        return pacienteRepository.save(paciente);
    }

    public Paciente getPacienteById(java.util.UUID id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado."));
    }

    public void deletePacienteById(java.util.UUID id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente não encontrado.");
        }
        pacienteRepository.deleteById(id);
    }
}