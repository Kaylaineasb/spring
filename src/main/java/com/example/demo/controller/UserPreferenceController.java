package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.model.UserPreference;
import com.example.demo.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferencias")

public class UserPreferenceController {
    @Autowired
    private UserPreferenceService userPreferenceService;

    @PostMapping
    public ResponseEntity<UserPreference> addPreference(@RequestBody UserPreference preference) {
        UserPreference savedPreference = userPreferenceService.save(preference);
        return ResponseEntity.ok(savedPreference);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Categoria>> getUserPreferences(@PathVariable Long userId) {
        List<Categoria> preferences = userPreferenceService.getUserPreferences(userId);
        return ResponseEntity.ok(preferences);
    }

    @DeleteMapping("/{userId}/{categoryId}")
    public ResponseEntity<Void> removePreference(@PathVariable Long userId, @PathVariable Long categoryId) {
        userPreferenceService.removePreference(userId, categoryId);
        return ResponseEntity.ok().build();
    }
}
