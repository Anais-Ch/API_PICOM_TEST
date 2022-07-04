package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotDao extends JpaRepository<TimeSlot, Integer> {
}