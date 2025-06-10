package com.cardapio.qrmenu.repository;

import com.cardapio.qrmenu.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT e FROM Empresa e LEFT JOIN FETCH e.categorias WHERE e.slug = :slug")
    Optional<Empresa> findBySlug(@Param("slug") String slug);
}