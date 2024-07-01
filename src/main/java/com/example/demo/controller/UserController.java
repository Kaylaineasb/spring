package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.model.Usuario;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.UserService;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//gerenciamento do usuário
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoriaService categoriaService;

    // Endpoint para adicionar categorias preferidas a um usuário existente.
    @PostMapping("/{userId}/categorias")
    public ResponseEntity<Usuario> adicionarCategoriasPreferidas(
            @PathVariable Long userId,
            @RequestBody Set<Long> categoriaIds) {

        // Busca o usuário pelo ID
        Usuario usuario = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));

        // Busca as categorias pelo IDs fornecidos
        List<Categoria> categorias = new ArrayList<>();
        for (Long categoriaId : categoriaIds) {
            Categoria categoria = categoriaService.findById(categoriaId);
            if (categoria != null) {
                categorias.add(categoria);
            }
        }

        // Adiciona as categorias preferidas ao usuário
        usuario.setCategoriasPreferidas(categorias); // mudei pra set pq n vi sentendo em chamar o metodo usuario.getCategoriasPreferidas().addAll(categorias);

        // Salva o usuário atualizado
        Usuario usuarioAtualizado = userService.save(usuario);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Categoria>> findCategoriaUsuario(@PathVariable Long id){
        Usuario usuario = userService.findById(id).orElse(null);
        if (usuario != null){
            List<Categoria> categorias = usuario.getCategoriasPreferidas();
            return ResponseEntity.ok().body(categorias);
        }
        return null;
    }
}
