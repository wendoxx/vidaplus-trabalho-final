package org.wendelsilva.vidaplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wendelsilva.vidaplus.dto.request.AdminRequestDTO;
import org.wendelsilva.vidaplus.model.Admin;
import org.wendelsilva.vidaplus.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin createAndUpdateAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin;
        if (adminRequestDTO.id() != null && adminRepository.existsById(adminRequestDTO.id())) {
            admin = adminRepository.findById(adminRequestDTO.id()).get();
        } else {
            admin = new Admin();
        }
        String senhaCriptografada = passwordEncoder.encode(adminRequestDTO.senha());
        admin.setNome(adminRequestDTO.nome());
        admin.setEmail(adminRequestDTO.email());
        admin.setSenha(senhaCriptografada);
        return adminRepository.save(admin);
    }
}
