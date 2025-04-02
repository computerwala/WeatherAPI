package com.weatherpincode.info.controller;

import com.weatherpincode.info.model.WeatherInfo;
import com.weatherpincode.info.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherInfo> getWeather(
            @RequestParam(name = "pincode") String pincode,
            @RequestParam(name = "for_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate forDate
    ) {
        WeatherInfo weatherInfo = weatherService.getWeather(pincode, forDate);
        return ResponseEntity.ok(weatherInfo);
    }
}