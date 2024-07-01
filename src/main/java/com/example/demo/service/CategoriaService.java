package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria findById(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + categoriaId));
    }

    public Categoria findByTipo(Categoria.Tipo tipoCategoria) {
        return categoriaRepository.findByTipo(tipoCategoria);
    }
}
