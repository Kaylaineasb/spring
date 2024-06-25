package com.example.demo.dto;

import java.time.LocalDate;

//encapsular os dados de registro de um novo usuário.
public record RegisterDTO (String login, String password, int role,  String nome, String fone, String email, LocalDate dataNascimento){
}
