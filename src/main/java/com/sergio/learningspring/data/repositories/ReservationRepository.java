package com.sergio.learningspring.data.repositories;

import com.sergio.learningspring.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    /* To add a new query to a Repo that is NOT already given by the CrudRepositoryExtension we can create
    * a new method in the interface.
    * The implementation (the actual transformation into SQL) is automatically done by Spring data
    * based on a convention on the method name.Spring data will Aspect it's implementation at runtime.
    *  */
    Iterable<Reservation> findReservationByReservationDate(Date date);
}
