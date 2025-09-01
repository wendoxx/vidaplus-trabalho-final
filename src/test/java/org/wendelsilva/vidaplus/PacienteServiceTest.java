package org.wendelsilva.vidaplus;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wendelsilva.vidaplus.dto.request.PacienteRequestDTO;
import org.wendelsilva.vidaplus.dto.response.PacienteResponseDTO;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.repository.PacienteRepository;
import org.wendelsilva.vidaplus.service.PacienteService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private Paciente paciente;
    private PacienteRequestDTO pacienteRequestDTO;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(UUID.randomUUID());
        paciente.setNome("João da Silva");
        paciente.setEmail("joao@email.com");

        pacienteRequestDTO = new PacienteRequestDTO(
                paciente.getId(),
                "João da Silva",
                new Date(),
                "123.456.789-00",
                "11999998888",
                "joao@email.com",
                "Rua Teste, 123"
        );
    }


    @Test
    void testGetPacienteById_WhenFound() {
        when(pacienteRepository.findById(paciente.getId())).thenReturn(Optional.of(paciente));

        PacienteResponseDTO result = pacienteService.getPacienteById(paciente.getId());

        assertNotNull(result);
        assertEquals(paciente.getNome(), result.getNome());
    }

    @Test
    void testGetPacienteById_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            pacienteService.getPacienteById(id);
        });
    }

    @Test
    void testDeletePacienteById_WhenFound() {
        when(pacienteRepository.existsById(paciente.getId())).thenReturn(true);
        doNothing().when(pacienteRepository).deleteById(paciente.getId());

        pacienteService.deletePacienteById(paciente.getId());

        verify(pacienteRepository, times(1)).deleteById(paciente.getId());
    }

    @Test
    void testDeletePacienteById_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(pacienteRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            pacienteService.deletePacienteById(id);
        });
    }
}
