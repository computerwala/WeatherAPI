package com.weatherpincode.info.repository;

import com.weatherpincode.info.model.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
    Optional<WeatherInfo> findByPincodeAndForDate(String pincode, LocalDate forDate);
}