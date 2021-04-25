package com.sergio.learningspring.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="RESERVATION")
public class Reservation {
    @Id
    @Column(name="RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

//    @Column(name="ROOM_ID")
//    private long roomId;
//
//    @Column(name="GUEST_ID")
//    private long guestId;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    @JsonBackReference // Breaks infinite recursion in Json serialization
    private Room room;

    @ManyToOne
    @JoinColumn(name = "GUEST_ID", referencedColumnName = "GUEST_ID")
    @JsonBackReference // Breaks infinite recursion in Json serialization
    private Guest guest;

    @Column(name = "RES_DATE")
    private Date reservationDate;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date resDate) {
        this.reservationDate = resDate;
    }
}
