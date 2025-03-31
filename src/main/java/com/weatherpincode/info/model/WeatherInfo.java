package com.weatherpincode.info.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pincode;
    private LocalDate forDate;

    @Column(columnDefinition = "TEXT")
    private String weatherData; // Store JSON response as string
}
