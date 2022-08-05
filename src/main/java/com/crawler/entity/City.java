package com.crawler.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue
    @Column(name = "CITY_ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @Column
    private String name;

    @Column
    private Integer population;

    @Column
    private Double density;

}
