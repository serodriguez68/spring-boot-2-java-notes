package com.sergio.learningspring.web;

import com.sergio.learningspring.business.domain.RoomReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sergio.learningspring.business.services.ReservationService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {

    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(@RequestParam(value = "date", required = false)String dateString, Model viewModel) {
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        /* Model here is a Spring View Model class */
        viewModel.addAttribute("roomReservations", roomReservations);
        /* This tells spring to go find an html template called "reservations".
        @see resources/templates/*
        * View templates also get access to viewModels to populate the dynamic bits.
        *  */
        return "reservations";
    }

}
