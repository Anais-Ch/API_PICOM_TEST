package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=true)
public interface CompanyDao extends JpaRepository<Company, String> {
}