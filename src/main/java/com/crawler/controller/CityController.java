package com.crawler.controller;

import com.crawler.entity.City;
import com.crawler.entity.Country;
import com.crawler.service.CityService;
import com.crawler.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(CityController.BASE_URL)
@RestController
public class CityController {

    public static final String BASE_URL = "/api/v1/cities";

    @Autowired
    private final CityService cityService;


    @GetMapping
    List<City> all() {
        return cityService.findAll();
    }

    @PostMapping
    City newCountry(@RequestBody City country) {
        return cityService.save(country);
    }

    @GetMapping("/{id}")
    Optional<City> one(@PathVariable Integer id) {
        return cityService.findById(id);
    }
}
