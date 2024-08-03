package com.example.demo.controller;

import com.example.demo.model.Noticia;
import com.example.demo.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//gerenciamento de notícias
@RestController
@RequestMapping("/noticias")

public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;


    //  Endpoint para retornar todas as notícias.
    @GetMapping("/all")
    public ResponseEntity<List<Noticia>> getAllNoticias() {
        List<Noticia> noticias = noticiaService.getAllNoticias();
        return ResponseEntity.ok(noticias);
    }

    //Endpoint para obter uma lista de notícias associadas a um usuário específico.
    @GetMapping
    public ResponseEntity<List<Noticia>> getNoticias(@RequestParam Long userId) {
        List<Noticia> noticias = noticiaService.getNoticias(userId);
        return ResponseEntity.ok(noticias);
    }
}
