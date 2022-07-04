package com.hb.PICOM_hibernate.buisness;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advertisment")
public class Advertisment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", nullable = false)
    private Integer id;

    @Column(name = "ad_name",nullable = false)
    @NotBlank(message = "The name of the advertisment is required.")
    @Size(min=2, max=80, message="The firstname should be between 2 and 80 characters")
    private String adName;

    @Column(name = "ad_created_at", nullable = false)
    private Instant adCreatedAt;

    @Column(name = "ad_started_at", nullable = false)
    //@FutureOrPresent(message = "The starting date should be either today or in the future days and can't be in the past")
    private Instant adStartedAt;

    @Column(name = "nb_days", nullable = false)
    @Positive(message = "The number of displayed days can't be negative or equal to 0.")
    private Integer nbDays;

    @Lob
    @Column(name = "ad_img_html")
    @NotBlank
    private String adImgHtml;

    @Column(name = "ad_img_alt")
    @NotBlank
    private String adImgAlt;

    @Column(name = "paid_at", nullable = true)
    private Instant paidAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull
    private User idUser;

    @OneToMany(mappedBy = "idAd")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "idAd")
    private List<TimeSlotList> timeSlotLists;

    //CONSTRUCTOR


    public Advertisment() {
        this.invoices= new ArrayList<Invoice>();
        this.timeSlotLists= new ArrayList<TimeSlotList>();
        this.adCreatedAt = Instant.now();
    }

    public Advertisment(String adName, Instant adStartedAt, Integer nbDays, String adImgHtml, String adImgAlt, Instant paidAt, User idUser) {
        this.adName = adName;
        this.adStartedAt = adStartedAt;
        this.nbDays = nbDays;
        this.adImgHtml = adImgHtml;
        this.adImgAlt = adImgAlt;
        this.paidAt = paidAt;
        this.idUser = idUser;
    }

    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public Instant getAdCreatedAt() {
        return adCreatedAt;
    }

    public void setAdCreatedAt(Instant adCreatedAt) {
        this.adCreatedAt = adCreatedAt;
    }

    public Instant getAdStartedAt() {
        return adStartedAt;
    }

    public void setAdStartedAt(Instant adStartedAt) {
        this.adStartedAt = adStartedAt;
    }

    public Integer getNbDays() {
        return nbDays;
    }

    public void setNbDays(Integer nbDays) {
        this.nbDays = nbDays;
    }

    public String getAdImgHtml() {
        return adImgHtml;
    }

    public void setAdImgHtml(String adImgHtml) {
        this.adImgHtml = adImgHtml;
    }

    public String getAdImgAlt() {
        return adImgAlt;
    }

    public void setAdImgAlt(String adImgAlt) {
        this.adImgAlt = adImgAlt;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

}