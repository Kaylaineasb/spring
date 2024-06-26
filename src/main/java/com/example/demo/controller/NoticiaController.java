package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.model.Noticia;
import com.example.demo.model.Usuario;
import com.example.demo.service.NoticiaService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//gerenciamento de notícias
@RestController
@RequestMapping("/noticias")

public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;
    @Autowired
    private UserService userService;


    //Endpoint para obter uma lista de notícias associadas a um usuário específico.
    //O método recebe um ID de usuário como parâmetro de consulta, busca as notícias
    //associadas a esse usuário através do serviço de notícias e retorna a lista de notícias.
    @GetMapping
    public ResponseEntity<List<Noticia>> getNoticias(@RequestParam Long userId) {
        List<Noticia> noticias = noticiaService.getNoticias(userId);
        return ResponseEntity.ok(noticias);
    }
    @PostMapping("/{userId}/categorias")
    public ResponseEntity<Usuario> adicionarCategoriasPreferidas(
            @PathVariable Long userId,
            @RequestBody Set<Long> categoriaIds) {

        Usuario usuario = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));

        Set<Categoria> categorias = new HashSet<>();
        for (Long categoriaId : categoriaIds) {
            Categoria categoria = userService.findCategoriaById(categoriaId);
            if (categoria != null) {
                categorias.add(categoria);
            }
        }

        usuario.getCategoriasPreferidas().addAll(categorias);
        Usuario usuarioAtualizado = userService.save(usuario);

        return ResponseEntity.ok(usuarioAtualizado);
    }

}
