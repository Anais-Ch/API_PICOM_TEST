package com.hb.PICOM_hibernate.buisness;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
@NoArgsConstructor
@ToString
public class Country {

    @Id
    @Column(name = "country_code", nullable = false, length = 3)
    @Pattern(regexp="^[a-z]{3}$",message = "The countryCode should be composed of only 3 characters")
    private String id;

    @Column(name = "country_name", nullable = false, length = 250)
    private String countryName;

    @JsonIgnore
    @OneToMany(mappedBy = "idCountry", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<City> cities;

    //Constructor
    public Country(String id, String countryName) {
        this.id = id;
        this.countryName = countryName;
        this.cities= new ArrayList<City>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}