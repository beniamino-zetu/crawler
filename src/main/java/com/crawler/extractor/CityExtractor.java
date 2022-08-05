package com.crawler.extractor;

import com.crawler.entity.City;
import com.crawler.entity.Country;
import com.crawler.service.CityService;
import com.crawler.service.CountryService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CityExtractor {

    static final String CITY_URL = "https://en.wikipedia.org/wiki/List_of_largest_cities";
    private static Logger logger = LogManager.getLogger(CityExtractor.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;


    public void extractCities() {
        List<City> cities = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(CITY_URL).get();

            Element table = doc.getElementsByClass("wikitable").get(0);

            Elements rows = table.getElementsByTag("TR");
            for (Element row : rows) {
                Elements tds;
                Elements ths;

                if (row.getElementsByClass("static-row-header").isEmpty()) {

                    ths = row.getElementsByTag("TH");
                    tds = row.getElementsByTag("TD");

                    cities.add(createCityFromElements(ths, tds));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Saving all cities");
        cityService.saveAll(cities);
    }

    private City createCityFromElements(Elements ths, Elements tds) {
        logger.info("Creating city from elements");

        String cityName = ths.get(0).text();
        String countryName = tds.get(0).text();
        Integer population = null;
        Double density = null;

        if (!tds.get(3).text().equals("—")) population = Integer.valueOf(tds.get(3).text().replaceAll(",", ""));

        if (!tds.get(5).text().equals("—")) {
            int bracketPos = tds.get(5).text().indexOf('[');
            String densityStr = tds.get(5).text();
            if (bracketPos > 0)
                densityStr = tds.get(5).text().replace(tds.get(5).text().substring(bracketPos - 1), "");
            density = Double.valueOf(densityStr.replace(",", ""));
        }

        Optional<Country> country = countryService.findByName(countryName);

        return City.builder().name(cityName).country(country.orElse(null)).population(population).density(density).build();
    }
}
