package com.akhdani.perdin_app.repository;

import com.akhdani.perdin_app.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCityName(String cityName);
}
