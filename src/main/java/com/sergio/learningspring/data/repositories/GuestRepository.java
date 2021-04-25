package com.sergio.learningspring.data.repositories;

import com.sergio.learningspring.data.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
    Iterable<Guest> findAllByOrderByLastNameAscFirstNameAsc();
}
