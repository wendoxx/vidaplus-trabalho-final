package org.wendelsilva.vidaplus.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.ConsultaRequestDTO;
import org.wendelsilva.vidaplus.dto.response.ConsultaResponseDTO;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.model.ConsultaStatus;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.repository.ConsultaRepository;
import org.wendelsilva.vidaplus.repository.MedicoRepository;
import org.wendelsilva.vidaplus.repository.PacienteRepository;

import java.util.UUID;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public Consulta createAndUpdateConsulta(ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta;
        if (consultaRequestDTO.id() != null && consultaRepository.existsById(consultaRequestDTO.id())) {
            consulta = consultaRepository.findById(consultaRequestDTO.id()).get();
        } else {
            consulta = new Consulta();
        }

        Paciente paciente = pacienteRepository.findById(consultaRequestDTO.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado."));

        Medico medico = medicoRepository.findById(consultaRequestDTO.medicoId())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado."));


        consulta.setDataHora(consultaRequestDTO.dataHora());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setMotivo(consultaRequestDTO.motivo());
        consulta.setStatus(consultaRequestDTO.status());
        consulta.setTipo(consultaRequestDTO.tipo());
        return consultaRepository.save(consulta);
    }

    public ConsultaResponseDTO getConsultaById(UUID id) {
        return consultaRepository.findById(id).map(ConsultaResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));
    }

    public void deleteConsultaById(UUID id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Consulta não encontrada.");
        }
        consultaRepository.deleteById(id);
    }

}
