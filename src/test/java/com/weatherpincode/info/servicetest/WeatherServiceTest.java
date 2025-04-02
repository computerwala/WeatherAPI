//package com.weatherpincode.info.servicetest;
//
//
//import com.weatherpincode.info.exception.WeatherApiException;
//import com.weatherpincode.info.model.WeatherInfo;
//import com.weatherpincode.info.repository.PincodeLocationRepository;
//import com.weatherpincode.info.repository.WeatherInfoRepository;
//import com.weatherpincode.info.service.WeatherService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class WeatherServiceTest {
//
//    @Mock
//    private PincodeLocationRepository pincodeRepo;
//
//    @Mock
//    private WeatherInfoRepository weatherRepo;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private WeatherService weatherService;
//
//    @Test
//    void getWeather_WhenDataNotCached_FetchesFromAPI() {
//        // Arrange
//        String pincode = "411014";
//        LocalDate date = LocalDate.now();
//
//        when(weatherRepo.findByPincodeAndForDate(pincode, date))
//                .thenReturn(Optional.empty());
//        when(pincodeRepo.findByPincode(pincode))
//                .thenReturn(Optional.empty());
//        when(restTemplate.getForEntity(anyString(), eq(Map.class)))
//                .thenReturn(new ResponseEntity<>(Map.of("lat", 18.52, "lon", 73.85), HttpStatus.OK));
//        when(restTemplate.getForObject(anyString(), eq(String.class)))
//                .thenReturn("{ \"temp\": 25.5 }");
//
//        // Act
//        WeatherInfo result = weatherService.getWeather(pincode, date);
//
//        // Assert
//        verify(weatherRepo, times(1)).save(any());
//        assertNotNull(result.getWeatherData());
//    }
//
//    @Test
//    void getWeather_WhenDataCached_ReturnsCachedData() {
//        // Arrange
//        String pincode = "411014";
//        LocalDate date = LocalDate.now();
//        WeatherInfo cachedWeather = new WeatherInfo();
//
//        when(weatherRepo.findByPincodeAndForDate(pincode, date))
//                .thenReturn(Optional.of(cachedWeather));
//
//        // Act
//        WeatherInfo result = weatherService.getWeather(pincode, date);
//
//        // Assert
//        verify(weatherRepo, never()).save(any());
//        assertEquals(cachedWeather, result);
//    }
//
//    @Test
//    void fetchAndSavePincode_InvalidResponse_ThrowsException() {
//        // Arrange
//        String pincode = "000000";
//        when(restTemplate.getForEntity(anyString(), eq(Map.class)))
//                .thenReturn(new ResponseEntity<>(Map.of(), HttpStatus.OK));
//
//        // Act & Assert
//        assertThrows(WeatherApiException.class, () -> weatherService.fetchAndSavePincode(pincode));
//    }
//
//    @Test
//    void fetchWeatherData_ApiFailure_ThrowsException() {
//        // Arrange
//        when(restTemplate.getForObject(anyString(), eq(String.class)))
//                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
//
//        // Act & Assert
//        assertThrows(WeatherApiException.class,
//                () -> weatherService.fetchWeatherData(18.52, 73.85));
//    }
//}