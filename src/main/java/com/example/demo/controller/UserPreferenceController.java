package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.model.UserPreference;
import com.example.demo.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//gerenciamento das preferências dos usuários.
@RestController
@RequestMapping("/preferencias")

public class UserPreferenceController {
    @Autowired
    private UserPreferenceService userPreferenceService;

    //Endpoint para adicionar uma nova preferência de usuário.
    //O método recebe um objeto UserPreference no corpo da requisição, salva a preferência
    //através do serviço de preferências de usuário e retorna a preferência salva.

    @PostMapping
    public ResponseEntity<UserPreference> addPreference(@RequestBody UserPreference preference) {
        UserPreference savedPreference = userPreferenceService.save(preference);
        return ResponseEntity.ok(savedPreference);
    }

    //Endpoint para obter as preferências de um usuário específico.
    //O método recebe o ID do usuário como um parâmetro de caminho, busca as preferências
    //do usuário através do serviço de preferências de usuário e retorna a lista de categorias.

    @GetMapping("/{userId}")
    public ResponseEntity<List<Categoria>> getUserPreferences(@PathVariable Long userId) {
        List<Categoria> preferences = userPreferenceService.getUserPreferences(userId);
        return ResponseEntity.ok(preferences);
    }

    //Endpoint para remover uma preferência de um usuário específico.
    //O método recebe o ID do usuário e o ID da categoria como parâmetros de caminho, remove a preferência
    //através do serviço de preferências de usuário e retorna uma resposta indicando o sucesso da operação.

    @DeleteMapping("/{userId}/{categoryId}")
    public ResponseEntity<Void> removePreference(@PathVariable Long userId, @PathVariable Long categoryId) {
        userPreferenceService.removePreference(userId, categoryId);
        return ResponseEntity.ok().build();
    }
}
