package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @Column(name = "num_SIREN", nullable = false, length = 9)
    private String id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_street", nullable = false)
    private String companyStreet;

    @Column(name = "company_street_number", nullable = false)
    private String companyStreetNumber;

    @Column(name = "company_address_complement")
    private String companyAddressComplement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_city", nullable = false)
    private City idCity;

    @OneToMany(mappedBy = "idCompany")
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "idCompany", cascade = CascadeType.REMOVE)
    private List<User> users;

    //CONSTRUCTOR//

    public Company(String siren,String companyName, String companyStreet, String companyStreetNumber, String companyAddressComplement, City idCity) {
        this.id=siren;
        this.companyName = companyName;
        this.companyStreet = companyStreet;
        this.companyStreetNumber = companyStreetNumber;
        this.companyAddressComplement = companyAddressComplement;
        this.idCity = idCity;
        this.paymentMethods = new ArrayList<PaymentMethod>();
        this.users = new ArrayList<User>();

    }

    //CONSTRUCTOR//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public String getCompanyStreetNumber() {
        return companyStreetNumber;
    }

    public void setCompanyStreetNumber(String companyStreetNumber) {
        this.companyStreetNumber = companyStreetNumber;
    }

    public String getCompanyAddressComplement() {
        return companyAddressComplement;
    }

    public void setCompanyAddressComplement(String companyAddressComplement) {
        this.companyAddressComplement = companyAddressComplement;
    }

    public City getIdCity() {
        return idCity;
    }

    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

}