package com.hb.PICOM_hibernate.dao;

import com.hb.PICOM_hibernate.buisness.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    /*get an user by his email
    * @param email email that we want to test for an user
    */

    @Query(
            """
            FROM User u
            WHERE u.email= ?1
            """
    )
    Optional<User> findUserByEmail(String email);









}