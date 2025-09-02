package org.wendelsilva.vidaplus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.model.Admin;
import org.wendelsilva.vidaplus.model.Medico;
import org.wendelsilva.vidaplus.model.Paciente;
import org.wendelsilva.vidaplus.model.Usuario;
import org.wendelsilva.vidaplus.repository.AdminRepository;
import org.wendelsilva.vidaplus.repository.MedicoRepository;
import org.wendelsilva.vidaplus.repository.PacienteRepository;
import org.wendelsilva.vidaplus.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(email));
        if (usuario.isPresent()) {

            return usuario.get();
        }

        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            Admin adminEncontrado = admin.get();
            return User.builder()
                    .username(adminEncontrado.getEmail())
                    .password(adminEncontrado.getSenha())
                    .roles("ADMIN")
                    .build();
        }


        Optional<Medico> medico = medicoRepository.findByEmail(email);
        if (medico.isPresent()) {
            Medico medicoEncontrado = medico.get();
            return User.builder()
                    .username(medicoEncontrado.getEmail())
                    .password(medicoEncontrado.getSenha())
                    .roles("MEDICO")
                    .build();
        }


        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if (paciente.isPresent()) {
            Paciente pacienteEncontrado = paciente.get();
            return User.builder()
                    .username(pacienteEncontrado.getEmail())
                    .password(pacienteEncontrado.getSenha())
                    .roles("PACIENTE")
                    .build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
    }
}