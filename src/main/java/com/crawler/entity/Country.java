package com.crawler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "COUNTRY_ID")
    private Integer id;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column
    private Integer population;

    @Column
    private Double area;

}
