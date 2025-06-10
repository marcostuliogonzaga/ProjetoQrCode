package com.cardapio.qrmenu.repository;

import com.cardapio.qrmenu.model.Categoria;
import com.cardapio.qrmenu.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByEmpresa(Empresa empresa);
}