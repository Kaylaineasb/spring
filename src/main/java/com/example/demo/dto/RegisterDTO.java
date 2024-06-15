package com.example.demo.dto;

import com.example.demo.model.UserRole;

public record RegisterDTO (String login, String password, UserRole role){
}
