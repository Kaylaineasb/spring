package com.example.demo.repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    void deleteByUserIdAndCategoriaId(Long userId, Long categoryId);

    @Query("SELECT up.categoria FROM UserPreference up WHERE up.user.id = :userId")
    List<Categoria> findCategoriesByUserId(Long userId);
}
