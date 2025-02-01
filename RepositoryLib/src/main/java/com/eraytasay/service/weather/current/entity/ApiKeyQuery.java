package com.eraytasay.service.weather.current.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "api_key_queries")
public class ApiKeyQuery {
    @Id
    @Column(name = "api_key_query_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "api_key", unique = true, nullable = false)
    public String apiKey;

    @Column(nullable = false)
    public int count;

    @OneToMany(mappedBy = "apiKeyQuery")
    public List<GeocodingQuery> geocodingQueries;

    @OneToMany(mappedBy = "apiKeyQuery")
    public List<CurrentWeatherDataApiQuery> currentWeatherDataApiQueries;
}
