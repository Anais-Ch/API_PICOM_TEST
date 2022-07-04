package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stop")
@NoArgsConstructor
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id", nullable = false)
    private Integer id;

    @Column(name = "stop_name", nullable = false)
    private String stopName;

    @Column(name = "ip_address", nullable = false, length = 15)
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_zone", nullable = false)
    private Zone idZone;

    //CONSTRUCTOR
    public Stop(String stopName, String ipAddress, Zone idZone) {
        this.stopName = stopName;
        this.ipAddress = ipAddress;
        this.idZone = idZone;
    }
    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Zone getIdZone() {
        return idZone;
    }

    public void setIdZone(Zone idZone) {
        this.idZone = idZone;
    }

}