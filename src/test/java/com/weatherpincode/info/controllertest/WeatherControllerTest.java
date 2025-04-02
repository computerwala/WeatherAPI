//package com.weatherpincode.info.controllertest;
//
//
//import com.weatherpincode.info.controller.WeatherController;
//import com.weatherpincode.info.exception.PincodeNotFoundException;
//import com.weatherpincode.info.model.WeatherInfo;
//import com.weatherpincode.info.service.WeatherService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import java.time.LocalDate;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(WeatherController.class)
//class WeatherControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private WeatherService weatherService;
//
//    @Test
//    void getWeather_ValidRequest_ReturnsOk() throws Exception {
//        // Arrange
//        WeatherInfo mockResponse = new WeatherInfo();
//        mockResponse.setPincode("411014");
//        mockResponse.setForDate(LocalDate.now());
//
//        when(weatherService.getWeather(any(), any()))
//                .thenReturn(mockResponse);
//
//        // Act & Assert
//        mockMvc.perform(get("/api/weather")
//                        .param("pincode", "411014")
//                        .param("for_date", "2023-10-15"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.pincode").value("411014"));
//    }
//
//    @Test
//    void getWeather_InvalidPincode_ReturnsNotFound() throws Exception {
//        // Arrange
//        when(weatherService.getWeather(any(), any()))
//                .thenThrow(new PincodeNotFoundException("000000"));
//
//        // Act & Assert
//        mockMvc.perform(get("/api/weather")
//                        .param("pincode", "000000")
//                        .param("for_date", "2023-10-15"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Pincode not found: 000000"));
//    }
//
//    @Test
//    void getWeather_MissingDateParam_ReturnsBadRequest() throws Exception {
//        mockMvc.perform(get("/api/weather")
//                        .param("pincode", "411014"))
//                .andExpect(status().isBadRequest());
//    }
//}