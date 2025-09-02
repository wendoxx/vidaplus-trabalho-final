//package org.wendelsilva.vidaplus;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.wendelsilva.vidaplus.config.TokenService;
//import org.wendelsilva.vidaplus.dto.request.RegistroRequestDTO;
//import org.wendelsilva.vidaplus.dto.response.RegistroResponseDTO;
//import org.wendelsilva.vidaplus.model.Role;
//import org.wendelsilva.vidaplus.model.Usuario;
//import org.wendelsilva.vidaplus.repository.UsuarioRepository;
//import org.wendelsilva.vidaplus.service.AuthService;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthServiceTest {
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//    @Mock
//    private TokenService tokenService;
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @InjectMocks
//    private AuthService authService;
//
//    @Test
//    void testRegister_Success() {
//        RegistroRequestDTO registroDTO = new RegistroRequestDTO("user@email.com", "User", "password", Role.USER);
//        Usuario newUser = new Usuario(registroDTO.email(), registroDTO.nome(), "encryptedPass", registroDTO.role());
//
//        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
//        when(usuarioRepository.save(any(Usuario.class))).thenReturn(newUser);
//
//        RegistroResponseDTO result = authService.register(registroDTO);
//
//        assertNotNull(result);
//        assertEquals(registroDTO.email(), result.getEmail());
//        assertEquals(registroDTO.role(), result.getRole());
//        verify(usuarioRepository, times(1)).save(any(Usuario.class));
//    }
//
//    @Test
//    void testRegister_WhenEmailAlreadyInUse() {
//        RegistroRequestDTO registroDTO = new RegistroRequestDTO("user@email.com", "User", "password", Role.USER);
//        when(usuarioRepository.existsByEmail(registroDTO.email())).thenReturn(true);
//
//        assertThrows(RuntimeException.class, () -> {
//            authService.register(registroDTO);
//        });
//    }
//}
