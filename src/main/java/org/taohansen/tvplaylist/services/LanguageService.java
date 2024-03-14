package org.taohansen.tvplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taohansen.tvplaylist.entities.Language;
import org.taohansen.tvplaylist.repositories.LanguageRepository;
import org.taohansen.tvplaylist.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
    public Language getLanguageById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id " + id));
    }
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }
    public void deleteLanguage(Long id) {
        Language language = getLanguageById(id);
        languageRepository.delete(language);
    }
}
