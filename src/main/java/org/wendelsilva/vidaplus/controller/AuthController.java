package org.wendelsilva.vidaplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wendelsilva.vidaplus.dto.request.LoginRequestDTO;
import org.wendelsilva.vidaplus.dto.request.RegistroRequestDTO;
import org.wendelsilva.vidaplus.dto.response.LoginResponseDTO;
import org.wendelsilva.vidaplus.dto.response.RegistroResponseDTO;
import org.wendelsilva.vidaplus.service.AuthService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<RegistroResponseDTO> register(@RequestBody RegistroRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authService.register(registerRequestDTO));
    }
}
