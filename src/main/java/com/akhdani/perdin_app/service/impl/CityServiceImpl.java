package com.akhdani.perdin_app.service.impl;

import com.akhdani.perdin_app.dto.CityRequest;
import com.akhdani.perdin_app.entity.City;
import com.akhdani.perdin_app.repository.CityRepository;
import com.akhdani.perdin_app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public void saveCity(CityRequest request) {
        City city = City.builder()
                .cityName(request.getCityName())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .province(request.getProvince())
                .island(request.getIsland())
                .luarNegeri(request.getLuarNegeri())
                .build();
        cityRepository.save(city);
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateCity(Long id, CityRequest request) {
        City city = getCityById(id);
        city.setCityName(request.getCityName());
        city.setLatitude(request.getLatitude());
        city.setLongitude(request.getLongitude());
        city.setProvince(request.getProvince());
        city.setIsland(request.getIsland());
        city.setLuarNegeri(request.getLuarNegeri());
        cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
