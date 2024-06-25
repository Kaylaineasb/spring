package com.example.demo.controller;

import com.example.demo.model.Noticia;
import com.example.demo.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/noticias")

public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public ResponseEntity<List<Noticia>> getNoticias(@RequestParam Long userId) {
        List<Noticia> noticias = noticiaService.getNoticias(userId);
        return ResponseEntity.ok(noticias);
    }
}
