package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.model.Usuario;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

//gerenciamento do usuário
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoriaService categoriaService;


    /* O método recebe um objeto Usuario no corpo da requisição, realiza o cadastro
     através do serviço de usuários e retorna o usuário cadastrado.*/

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario user) {
        Usuario newUser = userService.cadastrarUsuario(user);
        return ResponseEntity.ok(newUser);
    }
// Endpoint para adicionar categorias preferidas a um usuário existente.
@PostMapping("/{userId}/categorias")
public ResponseEntity<Usuario> adicionarCategoriasPreferidas(
        @PathVariable Long userId,
        @RequestBody Set<Long> categoriaIds) {

    // Busca o usuário pelo ID
    Usuario usuario = userService.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));

    // Busca as categorias pelo IDs fornecidos
    Set<Categoria> categorias = new HashSet<>();
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
}
