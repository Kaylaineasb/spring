package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.model.Noticia;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.NoticiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service

public class NoticiaService {
    @Autowired
    private NoticiaRepository noticiaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UserService userService;

    public List<Noticia> getNoticias(Long userId) {
        Set<Categoria> categorias = userService.consultarCategoriasPreferidas(userId);
        return noticiaRepository.findByCategoriaIn((List<Categoria>) categorias);
    }
    @Transactional
    public void associarCategorias(Long noticiaId, Long categoriaId) {
        Noticia noticia = noticiaRepository.findById(noticiaId)
                .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada: " + noticiaId));

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + categoriaId));

        noticia.setCategoria(categoria);

        noticiaRepository.save(noticia);
    }
}
