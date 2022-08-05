package com.crawler;

import com.crawler.extractor.CityExtractor;
import com.crawler.extractor.CountryExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CrawlerApplication {

    private static Logger logger = LogManager.getLogger(CrawlerApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CrawlerApplication.class, args);

        CountryExtractor countryExtractor = context.getBean(CountryExtractor.class);
        countryExtractor.extractCountries();

        CityExtractor cityExtractor = context.getBean(CityExtractor.class);
        cityExtractor.extractCities();

        logger.info("Finished!");

    }

}
