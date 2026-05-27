package com.akhdani.perdin_app.service;

import com.akhdani.perdin_app.dto.CityRequest;
import com.akhdani.perdin_app.entity.City;

import java.util.List;


public interface CityService {
    void saveCity(CityRequest request);

    List<City> getAllCity();

    City getCityById(Long id);

    void updateCity(Long id, CityRequest request);

    void deleteCity(Long id);
}
