package com.hb.PICOM_hibernate.service;

import com.hb.PICOM_hibernate.buisness.City;
import com.hb.PICOM_hibernate.dto.CityDto;

import java.util.List;

public interface CityService {

    List<City> fetchCities();

    City findById(Integer id);

    void deleteCity(City city);

    City addCity(City city);

    List<City> findByCountryCode(String countryCode);

    City findByPostalCodeAndCountry(String postalCode,String countryCode);


    City convertCityDtoToCity(CityDto cityDto);

}
