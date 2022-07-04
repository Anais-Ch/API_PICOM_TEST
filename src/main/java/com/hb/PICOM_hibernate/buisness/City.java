package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@NoArgsConstructor
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Integer id;

    @Column(name = "city_name", nullable = false)
    @Size(min = 2, max = 255, message="A city name should be between 2a nd 255 characters")
    private String cityName;

    @Column(name = "postal_code", nullable = false, length = 35)
    @Size(min=5, max=9 , message="A zip code should be between 5 and 9 caracters")
    private String postalCode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_country", nullable = false)
    private Country idCountry;

    @OneToMany(mappedBy = "idCity", cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private List<Company> companies;

    //CONSTRUCTOR
    public City(String cityName, String postalCode, Country idCountry) {
        this.cityName = cityName;
        this.postalCode = postalCode;
        this.idCountry = idCountry;
        this.companies= new ArrayList<Company>();
    }

    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Country idCountry) {
        this.idCountry = idCountry;
    }

}