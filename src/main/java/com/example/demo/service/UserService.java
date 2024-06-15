package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserService(UsuarioRepository usuarioRepository) {
        this.userRepository = usuarioRepository;
    }

    public Optional<Usuario> findById(Long id) {
        return userRepository.findById(id);
    }
    @Transactional
    public Usuario cadastrarUsuario(Usuario user) {
        // Realizar validações antes de salvar o usuário
        // Por exemplo, verificar se login e email já existem
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new RuntimeException("Login já cadastrado");
        }

        // Criptografar a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Outros métodos conforme necessário
}
