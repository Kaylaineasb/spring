package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.UserPreferenceRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserPreferenceService {
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Adicionar preferência
    public UserPreference addPreference(Long userId, Long categoryId) {
        var user = usuarioRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        var category = categoriaRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        UserPreference userPreference = new UserPreference();
        userPreference.setUser(user);
        userPreference.setCategoria(category);

        return userPreferenceRepository.save(userPreference);
    }
    // Método save para adicionar preferências do usuário
    public UserPreference save(UserPreference preference) {
        return userPreferenceRepository.save(preference);
    }

    // Remover preferência
    public void removePreference(Long userId, Long categoryId) {
        userPreferenceRepository.deleteByUserIdAndCategoriaId(userId, categoryId);
    }

    // Obter preferências do usuário
    public List<Categoria> getUserPreferences(Long userId) {
        return userPreferenceRepository.findCategoriesByUserId(userId);
    }
}
