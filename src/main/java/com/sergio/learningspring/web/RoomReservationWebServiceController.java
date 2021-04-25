package com.sergio.learningspring.web;

import com.sergio.learningspring.business.domain.RoomReservation;
import com.sergio.learningspring.business.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class RoomReservationWebServiceController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebServiceController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations(@RequestParam(name="date", required = false) String dateString) {
        /* Since we are not doing a web page, we don't need a model. */
        Date date = DateUtils.createDateFromDateString(dateString);
        /* Spring Boot automatically does the marshalling into JSON */
        return this.reservationService.getRoomReservationsForDate(date);
    }
}
