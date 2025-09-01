package org.wendelsilva.vidaplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.config.TokenService;
import org.wendelsilva.vidaplus.dto.request.LoginRequestDTO;
import org.wendelsilva.vidaplus.dto.request.RegistroRequestDTO;
import org.wendelsilva.vidaplus.dto.response.LoginResponseDTO;
import org.wendelsilva.vidaplus.dto.response.RegistroResponseDTO;
import org.wendelsilva.vidaplus.model.Usuario;
import org.wendelsilva.vidaplus.repository.UsuarioRepository;

@Service
public class AuthService {
    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((Usuario) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public RegistroResponseDTO register(RegistroRequestDTO registroRequestDTO) {
        if (this.usuarioRepository.existsByEmail(registroRequestDTO.email())) {
            throw new RuntimeException("Email already in use. Please use another email or login.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registroRequestDTO.senha());
        Usuario user = new Usuario(registroRequestDTO.email(), registroRequestDTO.nome(), encryptedPassword, registroRequestDTO.role());

        Usuario savedUser = this.usuarioRepository.save(user);

        return new RegistroResponseDTO(savedUser.getUsername(), savedUser.getRole());
    }

}
