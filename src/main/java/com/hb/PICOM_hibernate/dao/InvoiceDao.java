package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
}