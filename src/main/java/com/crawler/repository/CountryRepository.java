package com.crawler.repository;

import com.crawler.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT * from countries where country_name = :name",
            nativeQuery = true)
    Optional<Country> findByName(@Param("name") String name);
}
