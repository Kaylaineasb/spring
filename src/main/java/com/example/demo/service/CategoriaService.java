package com.example.demo.service;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public  void salvarCategoria(Categoria novaCategoria){
        Categoria categoria = categoriaRepository.findByNome(novaCategoria.getNome());
        if (categoria == null){
            categoriaRepository.save(novaCategoria);
        }
    }
}
