package com.hb.PICOM_hibernate.buisness;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_slots")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slots_id", nullable = false)
    private Integer id;

    @Column(name = "slot", nullable = false)
    private LocalTime slot;

    @Column(name = "time_slots_rate", nullable = false)
    private Double rate;

    @OneToMany(mappedBy = "idTimeSlot")
    private List<TimeSlotList> timeSlotLists;

    //CONSTRUCTOR


    public TimeSlot() {
        this.timeSlotLists = new ArrayList<TimeSlotList>();
    }

    public TimeSlot(LocalTime slot, Double rate) {
        this();
        this.slot = slot;
        this.rate = rate;
    }

    //CONSTRUCTOR

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getSlot() {
        return slot;
    }

    public void setSlot(LocalTime slot) {
        this.slot = slot;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}