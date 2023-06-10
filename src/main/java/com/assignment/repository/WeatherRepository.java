package com.assignment.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class WeatherRepository {
    Map<String, Weather> weatherStore = new HashMap<String, Weather>();

    public Weather save(Weather weather) {
        weatherStore.put(getKey(weather.getCity(), weather.getDate()), weather);
        return weather;
    }

    public List<Weather> findWeather(City city, LocalDate date) {
        Weather weather = weatherStore.get(getKey(city, date));
        List<Weather> result = new ArrayList<>();
        if (weather != null) {
            result.add(weather);
        }
        return result;
    }

    public String getKey(City city, LocalDate date) {
        return Optional.ofNullable(city).map(City::toString).orElse("") + "." +
                Optional.ofNullable(date).map(LocalDate::toString).orElse("");
    }
}
