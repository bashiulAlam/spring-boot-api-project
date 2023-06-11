package com.assignment.api;

import com.assignment.repository.City;
import com.assignment.repository.Weather;
import com.assignment.repository.WeatherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApiApplicationTests.class)
@WebMvcTest(WeatherController.class)
class ApiApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	WeatherRepository weatherRepository;

	@Test
	void test_001_UnauthorizedUserInGetWeatherAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/weather"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void test_002_UnauthorizedUserInPostWeatherAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/weather"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void test_003_CheckBlankResponseInGetWeatherAPI() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/weather?city=berlin").header("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
		mockMvc.perform(request).andExpect(status().isOk());
	}

	@Test
	void test_004_PostWeatherData() throws Exception {
		Weather weather = Weather.builder()
				.city(City.BERLIN)
				.date(LocalDate.parse("23/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.temperature(Float.valueOf(35))
				.build();

		Mockito.when(weatherRepository.save(weather)).thenReturn(weather);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/weather").header("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
		mockMvc.perform(request).andExpect(status().isOk());
	}
}
