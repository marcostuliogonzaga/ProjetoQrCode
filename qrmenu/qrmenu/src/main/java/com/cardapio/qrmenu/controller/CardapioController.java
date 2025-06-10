package com.cardapio.qrmenu.controller;

import com.cardapio.qrmenu.model.Empresa;
import com.cardapio.qrmenu.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CardapioController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/cardapio/{slug}")
    public String verCardapio(@PathVariable String slug, Model model) {
        Empresa empresa = empresaRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        
        model.addAttribute("empresa", empresa);
        return "cardapio/index";
    }
}