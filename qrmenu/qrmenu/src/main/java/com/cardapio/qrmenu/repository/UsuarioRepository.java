package com.cardapio.qrmenu.repository;

import com.cardapio.qrmenu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca usuário por email (para login)
    Optional<Usuario> findByEmail(String email);
    
    // Verifica se email já existe (para evitar duplicatas)
    boolean existsByEmail(String email);
}
