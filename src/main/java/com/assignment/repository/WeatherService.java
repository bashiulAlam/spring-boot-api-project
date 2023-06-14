package com.assignment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private final WeatherRepository weatherRepository;

    public List<Weather> findWeather(String city, String date) {
        LocalDate localDate = Optional.ofNullable(date)
                .map(dateString -> LocalDate.parse(dateString, dtFormatter))
                .orElse(null);
        City givenCity = Optional.ofNullable(city)
                .map(City::fromValue)
                .orElse(null);

        if (city != null && localDate == null)
            return weatherRepository.findByCity(givenCity);
        else if (city == null && localDate != null)
            return weatherRepository.findByDate(localDate);
        else if (city != null && localDate != null)
            return weatherRepository.findByCityAndDate(givenCity, localDate);
        else return Collections.emptyList();
    }

    public Weather save(Weather weather) {
        if (weather.getCity() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "city value cannot be empty or null");
        else if (weather.getDate() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "date value cannot be empty or null");
        else if (weather.getTemperature() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "temperature value cannot be empty or null");
        else {
            weather.setId(UUID.randomUUID());
            return weatherRepository.save(weather);
        }
    }
}