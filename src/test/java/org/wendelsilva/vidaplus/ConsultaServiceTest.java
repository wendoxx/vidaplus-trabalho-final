package org.wendelsilva.vidaplus;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wendelsilva.vidaplus.dto.request.ConsultaRequestDTO;
import org.wendelsilva.vidaplus.model.Consulta;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.repository.ConsultaRepository;
import org.wendelsilva.vidaplus.repository.MedicoRepository;
import org.wendelsilva.vidaplus.repository.PacienteRepository;
import org.wendelsilva.vidaplus.service.ConsultaService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;
    @Mock
    private PacienteRepository pacienteRepository;
    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private ConsultaService consultaService;

    private Paciente paciente;
    private Medico medico;
    private Consulta consulta;
    private ConsultaRequestDTO consultaRequestDTO;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(UUID.randomUUID());

        medico = new Medico();
        medico.setId(UUID.randomUUID());

        consulta = new Consulta();
        consulta.setId(UUID.randomUUID());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataHora(LocalDateTime.now());

        consultaRequestDTO = new ConsultaRequestDTO(
                consulta.getId(),
                paciente.getId(),
                medico.getId(),
                LocalDateTime.now(),
                "Consulta de rotina",
                "AGENDADA"
        );
    }

    @Test
    void testCreateAndUpdateConsulta() {
        when(pacienteRepository.findById(paciente.getId())).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(medico.getId())).thenReturn(Optional.of(medico));
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        Consulta result = consultaService.createAndUpdateConsulta(consultaRequestDTO);

        assertNotNull(result);
        assertEquals(paciente.getId(), result.getPaciente().getId());
        assertEquals(medico.getId(), result.getMedico().getId());
        verify(consultaRepository, times(1)).save(any(Consulta.class));
    }

    @Test
    void testCreateAndUpdateConsulta_WhenPacienteNotFound() {
        when(pacienteRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            consultaService.createAndUpdateConsulta(consultaRequestDTO);
        });
    }
}
