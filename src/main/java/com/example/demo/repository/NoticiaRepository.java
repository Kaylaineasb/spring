package com.example.demo.repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui
    List<Noticia> findByCategoria(Categoria categoria);
    boolean existsByTituloAndDataPublicacao(String titulo, Date dataPublicacao);
    @Query("SELECT COUNT(n) > 0 FROM Noticia n WHERE n.titulo = :titulo")
    boolean existsByTitulo(String titulo);

    Object findByTitulo(String noticiaDeTeste);
    List<Noticia> findByCategoriaIn(List<Categoria> categorias);
}

