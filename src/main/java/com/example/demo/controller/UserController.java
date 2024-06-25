package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//gerenciamento do usuário
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Endpoint para cadastro de novos usuários.
    //O método recebe um objeto Usuario no corpo da requisição, realiza o cadastro
    //através do serviço de usuários e retorna o usuário cadastrado.

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario user) {
        Usuario newUser = userService.cadastrarUsuario(user);
        return ResponseEntity.ok(newUser);
    }

}
