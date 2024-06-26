package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoriaRepository categoriaRepository;
    public UserService(UsuarioRepository usuarioRepository) {
        this.userRepository = usuarioRepository;
    }

    public Optional<Usuario> findById(Long id) {
        return userRepository.findById(id);
    }
    @Transactional
    public Usuario cadastrarUsuario(Usuario user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new RuntimeException("Login já cadastrado");
        }

        // Criptografar a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
    @Transactional
    public void adicionarCategoriaPreferida(Long userId, Long categoryId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        usuario.addCategoriaPreferida(categoria);
        userRepository.save(usuario);
    }
    @Transactional
    public void removerCategoriaPreferida(Long userId, Long categoryId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        usuario.removeCategoriaPreferida(categoria);
        userRepository.save(usuario);
    }

    public Set<Categoria> consultarCategoriasPreferidas(Long userId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return usuario.getCategoriasPreferidas();
    }

    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }
}
