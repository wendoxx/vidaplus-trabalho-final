package org.wendelsilva.vidaplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.MedicoRequestDTO;
import org.wendelsilva.vidaplus.dto.response.MedicoResponseDTO;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.repository.MedicoRepository;

import java.util.UUID;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico createAndUpdateMedico(MedicoRequestDTO medicoRequestDTO) {
        Medico medico;
        if (medicoRequestDTO.id() != null && medicoRepository.existsById(medicoRequestDTO.id())) {
            medico = medicoRepository.findById(medicoRequestDTO.id()).get();
        } else {
            medico = new Medico();
        }
        medico.setNome(medicoRequestDTO.nome());
        medico.setEspecialidade(medicoRequestDTO.especialidade());
        medico.setEmail(medicoRequestDTO.email());
        medico.setTelefone(medicoRequestDTO.telefone());
        medico.setSenha(medicoRequestDTO.senha());
        return medicoRepository.save(medico);
    }

    public MedicoResponseDTO getMedicoById(UUID id) {
        return medicoRepository.findById(id).map(MedicoResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));
    }

    public void deleteMedicoById(java.util.UUID id) {
        if (!medicoRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("Médico não encontrado.");
        }
        medicoRepository.deleteById(id);
    }
}
