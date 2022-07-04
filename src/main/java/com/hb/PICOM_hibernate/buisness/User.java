package com.hb.PICOM_hibernate.buisness;

import com.hb.PICOM_hibernate.buisness.Company;
import org.hibernate.collection.internal.PersistentList;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_firstname", nullable = false, length = 50)
    private String userFirstname;

    @Column(name = "user_lastname", nullable = false, length = 150)
    private String userLastname;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "international_code", nullable = false, length = 4)
    private String internationalCode;

    @Column(name = "user_created_at", nullable = false)
    private Instant userCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_company", nullable = false)
    private Company idCompany;

    @OneToMany(mappedBy = "idUser")
    private List<Advertisment> ads;

    @ManyToMany(fetch= FetchType.EAGER
    )
    @JoinTable(name="role_list",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name="role_id") }
    )
    private List<Role> roles;


    //CONSTRUCTOR

    public User() {
        this.userCreatedAt = Instant.now();
        this.ads= new ArrayList<Advertisment>();
        this.roles= new ArrayList<Role>();
    }

    public User(String userFirstname, String userLastname, String email, String password, String phone, String internationalCode, Company idCompany) {
        //invoke constructor with no arh to instantiate createdAt
        this();
        //set other params
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.internationalCode = internationalCode;
        this.idCompany = idCompany;
    }

    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public Instant getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserCreatedAt(Instant userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }

    public Company getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Company idCompany) {
        this.idCompany = idCompany;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role){
        this.roles.add(role);
    }


}