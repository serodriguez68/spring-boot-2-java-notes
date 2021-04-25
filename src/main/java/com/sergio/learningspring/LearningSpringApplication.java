package com.sergio.learningspring;

import com.sergio.learningspring.data.entity.Guest;
import com.sergio.learningspring.data.entity.Reservation;
import com.sergio.learningspring.data.entity.Room;
import com.sergio.learningspring.data.repositories.GuestRepository;
import com.sergio.learningspring.data.repositories.ReservationRepository;
import com.sergio.learningspring.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* This annotation:
* - Designates this class as the entrypoint of all the app
* - Initiates Spring's auto configuration based on the jars that are in our class path */
@SpringBootApplication
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	@RestController
	@RequestMapping("/rooms")
	public class RoomController {
		@Autowired
		private RoomRepository roomRepository;

		@GetMapping
		public Iterable<Room> getRooms() {
			/* findAll is NOT a method we created. It came via the CrudRepository extension from Srping Data  */
			return this.roomRepository.findAll();
		}
	}

	@RestController
	@RequestMapping("/guests")
	public class GuestController {
		@Autowired
		private GuestRepository guestRepository;

		@GetMapping
		public Iterable<Guest> getGuests() {
			/* findAll is NOT a method we created. It came via the CrudRepository extension from Srping Data  */
			return this.guestRepository.findAll();
		}
	}

	@RestController
	@RequestMapping("/reservations")
	public class ReservationController {
		@Autowired
		private ReservationRepository reservationRepository;

		@GetMapping
		public Iterable<Reservation> getReservations() {
			/* findAll is NOT a method we created. It came via the CrudRepository extension from Srping Data  */
			return this.reservationRepository.findAll();
		}
	}
}
