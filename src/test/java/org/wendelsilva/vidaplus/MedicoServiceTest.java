package org.wendelsilva.vidaplus;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wendelsilva.vidaplus.dto.request.MedicoRequestDTO;
import org.wendelsilva.vidaplus.dto.response.MedicoResponseDTO;
import org.wendelsilva.vidaplus.model.Especialidade;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.repository.MedicoRepository;
import org.wendelsilva.vidaplus.service.MedicoService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {
    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    private Medico medico;
    private MedicoRequestDTO medicoRequestDTO;

    @BeforeEach
    void setUp() {
        medico = new Medico();
        medico.setId(UUID.randomUUID());
        medico.setNome("Dra. Ana");
        medico.setEspecialidade(Especialidade.CARDIOLOGIA);

        medicoRequestDTO = new MedicoRequestDTO(
                medico.getId(),
                "Dra. Ana",
                Especialidade.CARDIOLOGIA,
                "123456/SP",
                "11988887777",
                "ana@email.com",
                "senha123"
        );
    }

    @Test
    void testCreateAndUpdateMedico() {
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        Medico result = medicoService.createAndUpdateMedico(medicoRequestDTO);

        assertNotNull(result);
        assertEquals("Dra. Ana", result.getNome());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    void testGetMedicoById_WhenFound() {
        when(medicoRepository.findById(medico.getId())).thenReturn(Optional.of(medico));

        MedicoResponseDTO result = medicoService.getMedicoById(medico.getId());

        assertNotNull(result);
        assertEquals(medico.getNome(), result.getNome());
    }

    @Test
    void testDeleteMedicoById_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(medicoRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            medicoService.deleteMedicoById(id);
        });
    }
}
