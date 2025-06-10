package com.cardapio.qrmenu.controller;

import com.cardapio.qrmenu.dto.AuthRequest;
import com.cardapio.qrmenu.model.Usuario;
import com.cardapio.qrmenu.repository.UsuarioRepository;
import com.cardapio.qrmenu.security.JwtUtil;
import com.cardapio.qrmenu.security.MeuUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MeuUserDetailsService userDetailsService;

    // Registro de usuário
    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    // Login e geração de token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getSenha())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(jwt);
    }

    // Apenas para testes
    @GetMapping("/teste")
    public String teste() {
        Usuario usuario = new Usuario();
        usuario.setEmail("marcostulio@gmail.com");
        usuario.setSenha(passwordEncoder.encode("123456"));
        usuarioRepository.save(usuario);
        return "Usuário Salvo... Vai no H2 ver";
    }
}
