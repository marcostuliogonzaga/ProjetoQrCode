package com.cardapio.qrmenu.controller;

import com.cardapio.qrmenu.model.Empresa;
import com.cardapio.qrmenu.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa criar(@RequestBody Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        return empresaRepository.findById(id).map(empresa -> {
            empresa.setNome(empresaAtualizada.getNome());
            empresa.setSlug(empresaAtualizada.getSlug());
            empresa.setCnpj(empresaAtualizada.getCnpj());
            empresa.setEmail(empresaAtualizada.getEmail());
            empresa.setTelefone(empresaAtualizada.getTelefone());
            empresa.setEndereco(empresaAtualizada.getEndereco());
            empresa.setUrlImagemLogo(empresaAtualizada.getUrlImagemLogo());
            empresaRepository.save(empresa);
            return ResponseEntity.ok(empresa);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
