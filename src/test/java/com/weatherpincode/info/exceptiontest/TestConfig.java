//package com.weatherpincode.info.exceptiontest;
//
//import com.weatherpincode.info.service.WeatherService;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//
//import static org.mockito.Mockito.mock;
//
//@TestConfiguration
//class TestConfig {
//    @Bean
//    @Primary // Override the real bean
//    public WeatherService weatherService() {
//        return mock(WeatherService.class);
//    }
//}