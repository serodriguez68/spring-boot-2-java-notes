package com.sergio.learningspring.web;

import com.sergio.learningspring.business.services.GuestService;
import com.sergio.learningspring.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestWebController {

    private final GuestService guestService;

    @Autowired
    public GuestWebController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String listGuests(Model viewModel) {
        List<Guest> guestList = guestService.getAllGuestsSortedByLastName();
        viewModel.addAttribute("guestList", guestList);
        return "guests";
    }
}
