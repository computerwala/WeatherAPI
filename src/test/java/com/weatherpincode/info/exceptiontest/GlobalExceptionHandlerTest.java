//import com.weatherpincode.info.configtest.TestConfig;
//import com.weatherpincode.info.exception.PincodeNotFoundException;
//import com.weatherpincode.info.service.WeatherService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.boot.autoconfigure.AutoConfigurationPackages.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Import(TestConfig.class) // Use test configuration
//class GlobalExceptionHandlerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WeatherService weatherService;
//
//    @Test
//    void handlePincodeNotFound_Returns404() throws Exception {
//        // Mock service to throw exception
//        when(weatherService.getWeather("000000", LocalDate.now()))
//                .thenThrow(new PincodeNotFoundException("000000"));
//
//        mockMvc.perform(get("/api/weather")
//                        .param("pincode", "000000")
//                        .param("for_date", "2023-10-15"))
//                .andExpect(status().isNotFound());
//    }
//}