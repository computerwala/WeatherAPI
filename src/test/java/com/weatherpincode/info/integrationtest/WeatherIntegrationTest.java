//package com.weatherpincode.info.integrationtest;
//
//
//import com.weatherpincode.info.repository.PincodeLocationRepository;
//import com.weatherpincode.info.repository.WeatherInfoRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class WeatherIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PincodeLocationRepository pincodeRepo;
//
//    @Autowired
//    private WeatherInfoRepository weatherRepo;
//
//    @Test
//    void fullIntegrationTest() throws Exception {
//        // Act
//        mockMvc.perform(get("/api/weather")
//                        .param("pincode", "411014")
//                        .param("for_date", "2023-10-15"))
//                .andExpect(status().isOk());
//
//        // Assert
//        assertTrue(pincodeRepo.findByPincode("411014").isPresent());
//        assertTrue(weatherRepo.findByPincodeAndForDate("411014", LocalDate.parse("2023-10-15")).isPresent());
//    }
//}