package com.crawler.service;

import com.crawler.entity.Country;
import com.crawler.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;


    public List<Country> findAll() {
        return countryRepository.findAll();
    }


    public Country save(Country country) {
        return countryRepository.save(country);
    }


    public Optional<Country> findById(Integer id) {
        return countryRepository.findById(id);
    }

    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }


    public List<Country> saveAll(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }
}
