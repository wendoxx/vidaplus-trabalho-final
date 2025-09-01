package org.wendelsilva.vidaplus.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.ConsultaRequestDTO;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.repository.ConsultaRepository;
import org.wendelsilva.vidaplus.repository.MedicoRepository;
import org.wendelsilva.vidaplus.repository.PacienteRepository;

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
        return consultaRepository.save(consulta);
    }
}
