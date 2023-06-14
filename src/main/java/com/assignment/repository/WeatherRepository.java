package com.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, String> {
    List<Weather> findByCity(City city);

    List<Weather> findByDate(LocalDate date);

    List<Weather> findByCityAndDate(City city, LocalDate date);
}
