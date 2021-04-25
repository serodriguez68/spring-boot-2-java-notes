package com.sergio.learningspring.business.services;

import com.sergio.learningspring.data.entity.Guest;
import com.sergio.learningspring.data.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuestsSortedByLastName() {
        List<Guest> guests = new ArrayList<>();
        guestRepository.findAllByOrderByLastNameAscFirstNameAsc().forEach(guests::add);
        return guests;
    }
}
