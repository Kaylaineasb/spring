package com.example.demo.dto;

import com.example.demo.model.UserRole;
import java.time.LocalDate;

public record RegisterDTO (String login, String password, int role,  String nome, String fone, String email, LocalDate dataNascimento){
}
