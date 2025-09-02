package org.wendelsilva.vidaplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.config.TokenService;
import org.wendelsilva.vidaplus.dto.request.LoginRequestDTO;
import org.wendelsilva.vidaplus.dto.response.LoginResponseDTO;

@Service
public class AuthService {
    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;



    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((UserDetails) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

}
