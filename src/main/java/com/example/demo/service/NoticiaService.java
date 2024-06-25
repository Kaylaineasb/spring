package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.model.Noticia;
import com.example.demo.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NoticiaService {
    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private UserPreferenceService userPreferenceService;

    //Obtém as notícias com base nas preferências do usuário.
    public List<Noticia> getNoticias(Long userId) {
        List<Categoria> categorias = userPreferenceService.getUserPreferences(userId);
        if (categorias.isEmpty()) {
            return noticiaRepository.findAll();
        } else {
            return noticiaRepository.findByCategoriaIn(categorias);
        }
    }
}
