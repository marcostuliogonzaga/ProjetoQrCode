package com.cardapio.qrmenu.controller;

import com.cardapio.qrmenu.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {
        return storageService.salvar(file);
    }
}