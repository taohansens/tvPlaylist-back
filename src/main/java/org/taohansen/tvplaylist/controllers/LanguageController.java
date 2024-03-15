package org.taohansen.tvplaylist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.taohansen.tvplaylist.entities.Language;
import org.taohansen.tvplaylist.services.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<Language> getAllLanguage() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public Language getLanguageId(@PathVariable Long id) {
        return languageService.getLanguageById(id);
    }
    
    @PostMapping
    public Language createLanguage(@RequestBody Language Language) {
        return languageService.createLanguage(Language);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
    }
}