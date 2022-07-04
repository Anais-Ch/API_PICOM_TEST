package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "time_slot_list")
@NoArgsConstructor
public class TimeSlotList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_list_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_time_slot", nullable = false)
    private TimeSlot idTimeSlot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ad", nullable = false)
    private Advertisment idAd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_zone", nullable = false)
    private Zone idZone;

    //CONSTRUCTOR//

    public TimeSlotList(TimeSlot idTimeSlot, Advertisment idAd, Zone idZone) {
        this.idTimeSlot = idTimeSlot;
        this.idAd = idAd;
        this.idZone = idZone;
    }


    //CONSTRICTOR //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TimeSlot getIdTimeSlot() {
        return idTimeSlot;
    }

    public void setIdTimeSlot(TimeSlot idTimeSlot) {
        this.idTimeSlot = idTimeSlot;
    }

    public Advertisment getIdAd() {
        return idAd;
    }

    public void setIdAd(Advertisment idAd) {
        this.idAd = idAd;
    }

    public Zone getIdZone() {
        return idZone;
    }

    public void setIdZone(Zone idZone) {
        this.idZone = idZone;
    }

}