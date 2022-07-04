package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment_method")
@NoArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id", nullable = false)
    private Integer id;

    @Column(name = "card_number", nullable = false, length = 19)
    private String cardNumber;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "CVC", nullable = false)
    private String cvc;

    @Column(name = "billing_firstname", nullable = false)
    private String billingFirstname;

    @Column(name = "billing_lastname", nullable = false)
    private String billingLastname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_company", nullable = false)
    private Company idCompany;

    //CONSTRUCTOR

    public PaymentMethod(String cardNumber, LocalDate expirationDate, String cvc, String billingFirstname, String billingLastname, Company idCompany) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.billingFirstname = billingFirstname;
        this.billingLastname = billingLastname;
        this.idCompany = idCompany;
    }


    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getBillingFirstname() {
        return billingFirstname;
    }

    public void setBillingFirstname(String billingFirstname) {
        this.billingFirstname = billingFirstname;
    }

    public String getBillingLastname() {
        return billingLastname;
    }

    public void setBillingLastname(String billingLastname) {
        this.billingLastname = billingLastname;
    }

    public Company getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Company idCompany) {
        this.idCompany = idCompany;
    }

}