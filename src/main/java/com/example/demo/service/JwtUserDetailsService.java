package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(usuario.getLogin(), usuario.getPassword(),
                new ArrayList<>());
    }
    public Usuario save(Usuario usuario) {
        Usuario newUser = new Usuario();
        newUser.setNome(usuario.getNome());
        newUser.setFone(usuario.getFone());
        newUser.setLogin(usuario.getLogin());
        newUser.setEmail(usuario.getEmail());
        newUser.setDataNascimento(usuario.getDataNascimento());
        newUser.setStatus("Ativo");
        newUser.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(newUser);
    }
}
