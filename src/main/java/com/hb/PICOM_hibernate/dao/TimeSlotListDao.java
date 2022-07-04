package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.TimeSlotList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotListDao extends JpaRepository<TimeSlotList, Integer> {
}