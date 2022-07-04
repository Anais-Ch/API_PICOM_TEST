package com.hb.PICOM_hibernate.service;

import com.hb.PICOM_hibernate.buisness.Country;

import java.util.List;

public interface CountryService {

    List<Country> fetchCountries();

    Country findById(String countryCode);

    void deleteCountry(Country country);

    Country addCountry(Country country);

    Country findByName(String countryName);
}
