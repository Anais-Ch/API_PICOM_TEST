package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}