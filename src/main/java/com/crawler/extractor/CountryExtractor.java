package com.crawler.extractor;

import com.crawler.entity.Country;
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

@Component
public class CountryExtractor {

    static final String COUNTRY_URL = "https://en.wikipedia.org/wiki/List_of_countries_by_population_in_2010";

    private static Logger logger = LogManager.getLogger(CountryExtractor.class);

    @Autowired
    private CountryService countryService;


    public void extractCountries() {
        List<Country> countries = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(COUNTRY_URL).get();

            Element table = doc.getElementsByClass("wikitable sortable").get(0);

            Elements rows = table.getElementsByTag("TR");
            for (Element row : rows) {
                Elements tds;

                if (row.getElementsByTag("TH").isEmpty() && row.getElementsContainingText("World").isEmpty()) {
                    tds = row.getElementsByTag("TD");

                    countries.add(createCountryFromElements(tds));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Saving all countries");
        countryService.saveAll(countries);
    }

    private Country createCountryFromElements(Elements tds) {

        logger.info("Creating new country from elements");
        String name = tds.get(1).text();
        Integer population = Integer.valueOf(tds.get(2).text().replace(",", ""));
        Double area = tds.get(4).text().isEmpty() ? null : Double.valueOf(tds.get(4).text().replace(",", ""));

        return Country.builder().
                countryName(name).
                population(population).
                area(area).build();
    }
}
