package com.crawler.controller;

import com.crawler.entity.Country;
import com.crawler.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping(CountryController.BASE_URL)
@RestController
public class CountryController {

    public static final String BASE_URL = "/api/v1/countries";

    @Autowired
    private final CountryService countryService;


    @GetMapping
    List<Country> all() {
        return countryService.findAll();
    }

    @PostMapping
    Country newCountry(@RequestBody Country country) {
        return countryService.save(country);
    }

    @GetMapping("/{id}")
    Optional<Country> one(@PathVariable Integer id) {
        return countryService.findById(id);
    }
}
