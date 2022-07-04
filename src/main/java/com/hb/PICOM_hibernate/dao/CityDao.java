package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.City;
import com.hb.PICOM_hibernate.buisness.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface CityDao extends JpaRepository<City, Integer> {

    @Query("""
    FROM City c 
    WHERE c.idCountry.id = :countryCode
    """)
    List<City> findByIdCountry(String countryCode);

    @Query("""
        FROM City c 
        WHERE c.postalCode=:postalCode AND c.idCountry.id=:countryCode
    """)
    City findByPostalCodeAndCountry(String postalCode, String countryCode);
}