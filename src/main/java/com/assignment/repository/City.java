package com.assignment.repository;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
public enum City {
    BERLIN("berlin"),
    MUNICH("munich"),
    HAMBURG("hamburg");

    private String value;

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static City fromValue(final String value) {
        for (City c : City.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "city value cannot be empty or null");
    }
}
