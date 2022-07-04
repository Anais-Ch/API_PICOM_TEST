package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodDao extends JpaRepository<PaymentMethod, Integer> {
}