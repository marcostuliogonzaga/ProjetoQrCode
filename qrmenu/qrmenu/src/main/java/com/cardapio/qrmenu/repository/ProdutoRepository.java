package com.cardapio.qrmenu.repository;

import com.cardapio.qrmenu.model.Categoria;
import com.cardapio.qrmenu.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria(Categoria categoria);
    
    @Query("SELECT p FROM Produto p WHERE p.categoria.empresa.id = :empresaId")
    List<Produto> findByEmpresaId(@Param("empresaId") Long empresaId);
}