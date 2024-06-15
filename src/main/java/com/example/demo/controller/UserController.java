package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario user) {
        Usuario newUser = userService.cadastrarUsuario(user);
        return ResponseEntity.ok(newUser);
    }

    // Outros endpoints para atualização, consulta, exclusão, etc.
}
