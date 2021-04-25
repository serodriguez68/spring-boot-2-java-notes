package com.sergio.learningspring.business.services;

import com.sergio.learningspring.business.domain.RoomReservation;
import com.sergio.learningspring.data.entity.Guest;
import com.sergio.learningspring.data.entity.Reservation;
import com.sergio.learningspring.data.entity.Room;
import com.sergio.learningspring.data.repositories.GuestRepository;
import com.sergio.learningspring.data.repositories.ReservationRepository;
import com.sergio.learningspring.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* @Service labels the class as a service.
* Other than making it available as a Bean in spring, this annotation by default does nothing more (i.e. in this
* regard, it is equivalent to using @Component).
* However, if we are consistent with labelling our services with @Service, then we can program ASPECTS using this
* annotation that apply to all services (like wrapping public methods with transactions, or logging).
*   */
@Service
public class ReservationService {
    /* They are marked as final because once the the injected dependency is set, they should not change */
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    /* If you only have one constructor, you do not need to specify this. However this makes it explicit that this is
    *  the constructor that Spring should use to instantiate this class.  If we have multiple constructors, only
    * one can be @Autowired. */
    @Autowired
    /* The constructor is used by Spring to inject the dependencies */
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        /* This code is NOT optimized at all, and is unnecessarily convoluted.
        This is just sample code that could be simplified by using other Spring Data features not shown in this
        tutorial */
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(
                new java.sql.Date(date.getTime())
        );
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getReservationId());
            roomReservation.setDate(date);
            Guest guest = reservation.getGuest();
            roomReservation.setGuestId(guest.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        return roomReservations;
    }
}
