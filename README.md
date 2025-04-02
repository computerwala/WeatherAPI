```markdown
# Weather Info by Pincode

A Spring Boot REST API to fetch weather information for a given pincode and date, leveraging OpenWeatherMap APIs and caching to optimize performance.

---

## Features

- **REST API** to retrieve weather data by pincode and date.
- **Caching** for geocoding (pincode → lat/long) and weather data to minimize external API calls.
- **H2 Database** for storing pincode coordinates and weather data.
- **Exception Handling** for invalid pincodes, missing parameters, and API failures.
- **Unit and Integration Tests** with 90%+ coverage.

---

## Technologies

- **Backend**: Spring Boot 3.x
- **Database**: H2 (in-memory)
- **Caching**: Caffeine
- **APIs**: 
  - OpenWeatherMap Geocoding API
  - OpenWeatherMap Current Weather API
- **Testing**: JUnit 5, Mockito, Spring MockMvc
- **Tools**: Lombok, Maven

---

## Prerequisites

- Java 17+
- Maven 3.8+
- [OpenWeatherMap API Key](https://openweathermap.org/api) (free tier)

---

## Setup & Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/weather-info-by-pincode.git
   cd weather-info-by-pincode
   ```

2. **Add your OpenWeatherMap API key**:
   - Update `src/main/resources/application.properties`:
     ```properties
     openweather.api.key=your_api_key_here
     ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

---

## API Documentation

### Get Weather by Pincode and Date
```http
GET /api/weather?pincode={pincode}&for_date={date}
```

#### Parameters
| Name      | Type   | Description                     | Example        |
|-----------|--------|---------------------------------|----------------|
| `pincode` | String | Valid Indian pincode            | `411014`       |
| `for_date`| Date   | Date in `YYYY-MM-DD` format     | `2023-10-15`   |

#### Example Request
```bash
curl "http://localhost:8080/api/weather?pincode=411014&for_date=2023-10-15"
```

#### Example Response
```json
{
  "id": 1,
  "pincode": "411014",
  "forDate": "2023-10-15",
  "weatherData": "{ \"coord\": { \"lon\": 73.8553, \"lat\": 18.5196 }, ... }"
}
```

---

## Caching Strategy

| Component                | Cache Duration | Storage       |
|--------------------------|----------------|---------------|
| **Pincode → Coordinates**| 24 hours       | H2 Database   |
| **Weather Data**         | 1 hour         | H2 Database   |

---

## Testing

### Run Tests
```bash
mvn test
```

### Test Coverage
- **Service Layer**: Business logic, caching, and API error handling.
- **Controller Layer**: HTTP status codes, parameter validation.
- **Integration Tests**: End-to-end database and API interactions.

---

## H2 Database Console

Access the in-memory H2 database at:  
`http://localhost:8080/h2-console`

**Credentials**:
- JDBC URL: `jdbc:h2:mem:weatherdb`
- Username: `sa`
- Password: (leave empty)

---

## Troubleshooting

- **401 Unauthorized**: Ensure `openweather.api.key` is valid.
- **404 Not Found**: Verify the pincode exists (e.g., `411014` for Pune).
- **500 Errors**: Check application logs for details.


---

