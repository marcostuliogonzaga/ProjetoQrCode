package com.cardapio.qrmenu.controller;

import com.cardapio.qrmenu.model.Categoria;
import com.cardapio.qrmenu.model.Empresa;
import com.cardapio.qrmenu.repository.CategoriaRepository;
import com.cardapio.qrmenu.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    // Listar categorias da empresa pelo slug
    @GetMapping("/empresa/{slug}")
    public List<Categoria> listarCategoriasPorEmpresa(@PathVariable String slug) {
        Empresa empresa = empresaRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        return categoriaRepository.findByEmpresa(empresa);
    }
}
