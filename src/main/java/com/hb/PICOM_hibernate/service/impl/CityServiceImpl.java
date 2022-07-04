package com.hb.PICOM_hibernate.service.impl;

import com.hb.PICOM_hibernate.buisness.City;
import com.hb.PICOM_hibernate.buisness.Country;
import com.hb.PICOM_hibernate.dao.CityDao;
import com.hb.PICOM_hibernate.dto.CityDto;
import com.hb.PICOM_hibernate.service.CityService;
import com.hb.PICOM_hibernate.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityDao cityDao;
    private CountryService countryService;


    @Override
    public List<City> fetchCities() {
        return cityDao.findAll();
    }

    @Override
    public City findById(Integer id) {
        return cityDao.findById(id).orElse(null);
    }

    @Override
    public void deleteCity(City city) {
        cityDao.delete(city);
    }

    @Override
    public City addCity(City city) {
        return cityDao.save(city);
    }

    @Override
    public List<City> findByCountryCode(String countryCode) {
        return cityDao.findByIdCountry(countryCode);
    }

    @Override
    public City findByPostalCodeAndCountry(String postalCode, String countryCode) {
        return cityDao.findByPostalCodeAndCountry(postalCode,countryCode);
    }

    @Override
    public City convertCityDtoToCity(CityDto cityDto) {

        Country country = countryService.findById(cityDto.getCountryCode());
        System.out.println("id="+country.getId());

        City newCity = new City();
        newCity.setCityName(cityDto.getCityName());
        newCity.setPostalCode(cityDto.getPostalCode());
        newCity.setIdCountry(country);


        return newCity;
    }
}
