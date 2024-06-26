package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario user;
    @ManyToOne
    private Categoria categoria;

}
