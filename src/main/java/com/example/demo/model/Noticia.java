package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.Objects;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String imagem;
    private String descricao;
    private String link;
    private Date dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Noticia)) return false;
        Noticia noticia = (Noticia) o;
        return Objects.equals(getId(), noticia.getId());
    }



    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}