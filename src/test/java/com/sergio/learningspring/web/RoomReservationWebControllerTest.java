package com.sergio.learningspring.web;


import com.sergio.learningspring.business.domain.RoomReservation;
import com.sergio.learningspring.business.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Part of the setup to use MockMvc
public class RoomReservationWebControllerTest {
    @MockBean
    private ReservationService reservationService;

    @Autowired
    /* Allows us to simulate requests to the controllers without having to start the HTTP server */
    private MockMvc mockMvc;

    /* This test shows how to do a sort-of mvc level unit test by mocking out the only dependency that is NOT inside
    the "web" package (i.e. the reservationService).
    This is NOT an integration test for that reason.
     */
    @Test
    public void getReservations() throws Exception {
        String dateString = "202-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = new ArrayList<>();
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setLastName("Unit");
        roomReservation.setFirstName("JUnit");
        roomReservation.setDate(date);
        roomReservation.setGuestId(1);
        roomReservation.setRoomId(100);
        roomReservation.setRoomName("JUnit Room");
        roomReservation.setRoomNumber("J1");
        roomReservations.add(roomReservation);
        // Mocking of the reservationService
        given(reservationService.getRoomReservationsForDate(date)).willReturn(roomReservations);

        this.mockMvc.perform(get("/reservations?date=" + dateString))
                .andExpect(status().isOk())
                // Content is the whole page as a string
                .andExpect(content().string(containsString("Unit, JUnit")));
    }
}
