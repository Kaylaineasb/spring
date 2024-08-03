package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.model.Noticia;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.NoticiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NoticiaService {
    @Autowired
    private NoticiaRepository noticiaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UserService userService;

    // Método para buscar todas as notícias
    public List<Noticia> getAllNoticias() {
        return noticiaRepository.findAll();
    }

    // Outros métodos existentes, por exemplo:
    public List<Noticia> getNoticias(Long userId) {
        List<Categoria> categorias = userService.consultarCategoriasPreferidas(userId);
        return noticiaRepository.findByCategoriaIn(categorias);
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
