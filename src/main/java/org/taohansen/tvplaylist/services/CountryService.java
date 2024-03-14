package org.taohansen.tvplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taohansen.tvplaylist.entities.Country;
import org.taohansen.tvplaylist.entities.Language;
import org.taohansen.tvplaylist.repositories.CountryRepository;
import org.taohansen.tvplaylist.services.exceptions.ResourceNotFoundException;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));
    }
    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }
    public void deleteCountry(Long id) {
        Country country = getCountryById(id);
        countryRepository.delete(country);
    }
}