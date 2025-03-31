package com.weatherpincode.info.service;

import com.weatherpincode.info.exception.PincodeNotFoundException;
import com.weatherpincode.info.exception.WeatherApiException;
import com.weatherpincode.info.model.PincodeLocation;
import com.weatherpincode.info.model.WeatherInfo;
import com.weatherpincode.info.repository.PincodeLocationRepository;
import com.weatherpincode.info.repository.WeatherInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final PincodeLocationRepository pincodeRepo;
    private final WeatherInfoRepository weatherRepo;
    private final RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.geocode.url}")
    private String geocodeUrl;

    @Value("${openweather.weather.url}")
    private String weatherUrl;

    public WeatherInfo getWeather(String pincode, LocalDate forDate) {
        return weatherRepo.findByPincodeAndForDate(pincode, forDate)
                .orElseGet(() -> fetchAndSaveWeather(pincode, forDate));
    }

    private WeatherInfo fetchAndSaveWeather(String pincode, LocalDate forDate) {
        PincodeLocation location = pincodeRepo.findByPincode(pincode)
                .orElseGet(() -> fetchAndSavePincode(pincode));

        String weatherJson = fetchWeatherData(location.getLat(), location.getLon());

        WeatherInfo weather = new WeatherInfo();
        weather.setPincode(pincode);
        weather.setForDate(forDate);
        weather.setWeatherData(weatherJson);
        return weatherRepo.save(weather);
    }

    private PincodeLocation fetchAndSavePincode(String pincode) {
        try {
            String url = String.format("%s?zip=%s,IN&appid=%s", geocodeUrl, pincode, apiKey);
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new PincodeNotFoundException(pincode);
            }

            Map<String, Object> data = response.getBody();
            if (!data.containsKey("lat") || !data.containsKey("lon")) {
                throw new WeatherApiException("Invalid geocoding response");
            }

            PincodeLocation location = new PincodeLocation();
            location.setPincode(pincode);
            location.setLat(Double.parseDouble(data.get("lat").toString()));
            location.setLon(Double.parseDouble(data.get("lon").toString()));
            return pincodeRepo.save(location);

        } catch (HttpClientErrorException e) {
            throw new PincodeNotFoundException(pincode);
        }
    }

    private String fetchWeatherData(Double lat, Double lon) {
        try {
            String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric",
                    weatherUrl, lat, lon, apiKey);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new WeatherApiException("Failed to fetch weather data");
            }
            return response.getBody();

        } catch (HttpClientErrorException e) {
            throw new WeatherApiException("OpenWeather API error: " + e.getStatusCode());
        }
    }
}