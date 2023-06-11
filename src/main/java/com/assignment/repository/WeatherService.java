package com.assignment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

        if (localDate == null)
            return weatherRepository.findWeatherByCity(givenCity);
        else if (city == null)
            return weatherRepository.findWeatherByDate(localDate);
        else
            return weatherRepository.findWeather(givenCity, localDate);
    }

    public Weather save(Weather weather) {
        return weatherRepository.save(weather);
    }
}