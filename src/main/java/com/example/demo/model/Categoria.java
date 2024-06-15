package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(Set<Noticia> noticias) {
        this.noticias = noticias;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
