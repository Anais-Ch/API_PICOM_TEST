package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryDao extends JpaRepository<Country, String> {


    public Country findByCountryName(String string);
}