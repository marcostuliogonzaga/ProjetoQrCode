package com.cardapio.qrmenu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Service
public class FileStorageService {
    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar a pasta uploads");
        }
    }

    public String salvar(MultipartFile file) {
        try {
            Path destino = this.root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + file.getOriginalFilename();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar arquivo");
        }
    }
}