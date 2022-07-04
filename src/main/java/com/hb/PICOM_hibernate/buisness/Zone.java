package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zone")

public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id", nullable = false)
    private Integer id;

    @Column(name = "zone_name", nullable = false)
    private String zoneName;

    @Column(name = "zone_price", nullable = false)
    private Double zonePrice;

    @Lob
    @Column(name = "zone_description", nullable = false)
    private String zoneDescription;

    @Lob
    @Column(name = "zone_img_src", nullable = false)
    private String zoneImgSrc;

    @Column(name = "zone_img_alt", nullable = false)
    private String zoneImgAlt;


    @OneToMany(mappedBy = "idZone")
    private List<TimeSlotList> timeSlotLists;

    @OneToMany(mappedBy = "idZone")
    private List<Stop> stops;

    //CONSTRUCTOR


    public Zone() {
        this.timeSlotLists = new ArrayList<TimeSlotList>();
        this.stops = new ArrayList<Stop>();
    }

    public Zone(String zoneName, Double zonePrice, String zoneDescription, String zoneImgSrc, String zoneImgAlt) {
        this();
        this.zoneName = zoneName;
        this.zonePrice = zonePrice;
        this.zoneDescription = zoneDescription;
        this.zoneImgSrc = zoneImgSrc;
        this.zoneImgAlt = zoneImgAlt;

    }

    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Double getZonePrice() {
        return zonePrice;
    }

    public void setZonePrice(Double zonePrice) {
        this.zonePrice = zonePrice;
    }

    public String getZoneDescription() {
        return zoneDescription;
    }

    public void setZoneDescription(String zoneDescription) {
        this.zoneDescription = zoneDescription;
    }

    public String getZoneImgSrc() {
        return zoneImgSrc;
    }

    public void setZoneImgSrc(String zoneImgSrc) {
        this.zoneImgSrc = zoneImgSrc;
    }

    public String getZoneImgAlt() {
        return zoneImgAlt;
    }

    public void setZoneImgAlt(String zoneImgAlt) {
        this.zoneImgAlt = zoneImgAlt;
    }


}