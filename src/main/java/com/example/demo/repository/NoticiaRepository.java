package com.example.demo.repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui
    List<Noticia> findByCategoria(Categoria categoria);
}

