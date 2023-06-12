package com.assignment.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.json.JsonObjectMapper;

import java.time.LocalDate;
import java.util.*;

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
        else {
            //Jedis jedis = new Jedis("localhost", 8080);
            //jedis.connect();
            weatherList.add(weather);
            //jedis.set("weatherList", new Gson().toJson(weatherList));
        }

        return weather;
    }

    public List<Weather> findWeatherByCity(City city) {
        //Jedis jedis = new Jedis("localhost", 8080);
        //jedis.connect();
        //String jsonWeatherList = jedis.get("weatherList");
        //List<Weather> retrievedWeatherList = new Gson().fromJson(jsonWeatherList, new TypeToken<List<Weather>>() {}.getType());

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
