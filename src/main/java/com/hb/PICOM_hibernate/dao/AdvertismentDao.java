package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.Advertisment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertismentDao extends JpaRepository<Advertisment, Integer> {
}