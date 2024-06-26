package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "categoria")
    private Set<Noticia> noticias;

    @ManyToMany(mappedBy = "categoriasPreferidas")
    private Set<Usuario> usuarios;


}
