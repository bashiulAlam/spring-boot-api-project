package com.assignment.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class WeatherRepository {
    private List<Weather> weatherList = new ArrayList<>();

    public Weather save(Weather weather) {
        if (weather.getCity() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "city value cannot be empty or null");
        else if (weather.getDate() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "date value cannot be empty or null");
        else if (weather.getTemperature() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "temperature value cannot be empty or null");
        else
            weatherList.add(weather);

        return weather;
    }

    public List<Weather> findWeatherByCity(City city) {
        List<Weather> result = new ArrayList<>();
        for (Weather weather : weatherList) {
            if (weather.getCity().equals(city)) {
                result.add(weather);
            }
        }
        return result;
    }

    public List<Weather> findWeatherByDate(LocalDate date) {
        List<Weather> result = new ArrayList<>();
        for (Weather weather : weatherList) {
            if (weather.getDate().equals(date)) {
                result.add(weather);
            }
        }
        return result;
    }

    public List<Weather> findWeather(City city, LocalDate date) {
        List<Weather> result = new ArrayList<>();
        for (Weather weather : weatherList) {
            if (weather.getCity().equals(city) && weather.getDate().equals(date)) {
                result.add(weather);
            }
        }
        return result;
    }
}
