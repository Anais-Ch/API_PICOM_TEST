package com.hb.PICOM_hibernate.service.impl;

import com.hb.PICOM_hibernate.buisness.Country;
import com.hb.PICOM_hibernate.dao.CountryDao;
import com.hb.PICOM_hibernate.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;

    @Override
    public List<Country> fetchCountries() {
        return countryDao.findAll();
    }

    @Override
    public Country findById(String countryCode) {
        return countryDao.findById(countryCode).orElse(null);
    }

    @Override
    public void deleteCountry(Country country) {
        countryDao.delete(country);

    }

    //@Transactionnal cr&er une transaction vérfiei qu'elel est possible et l'envoie en bse
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Country addCountry(Country country) {
        return countryDao.save(country);
    }

    @Override
    public Country findByName(String countryName) {
        return countryDao.findByCountryName(countryName);
    }


}
