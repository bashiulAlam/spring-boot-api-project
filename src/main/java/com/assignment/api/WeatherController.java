package com.assignment.api;

import com.assignment.repository.Weather;
import com.assignment.repository.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public List<Weather> getWeather(@RequestParam(value = "city", required = false) String city,
                                    @RequestParam(value = "date", required = false) String date) {

        return weatherService.findWeather(city, date);
    }

    @PostMapping("/weather")
    public Weather postWeather(@RequestBody Weather weather) {
        return weatherService.save(weather);
    }
}