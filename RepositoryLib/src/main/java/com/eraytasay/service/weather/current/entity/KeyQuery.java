package com.eraytasay.service.weather.current.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "key_queries")
public class KeyQuery {
    @Id
    @Column(name = "key_query_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(unique = true, nullable = false)
    public String key;

    @Column(nullable = false)
    public int count;

    @OneToMany(mappedBy = "key_query_id")
    public List<GeocodingQuery> m_geocodingQueries;

    @OneToMany(mappedBy = "key_query_id")
    public List<CurrentWeatherDataApiQuery> currentWeatherDataApiQueries;
}
