package com.sergio.learningspring.data.repositories;

import com.sergio.learningspring.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* CrudRepository is part of Spring Data and it is an interface for generic CRUD operations
on a specific Entity Type.
The generic argument it takes are: CrudRepository<InvolvedEntity, ClassOfId>
By doing this wiring, we get a bunch of working methods (behaviour) for free. Spring data makes the automatic
behaviour of repositories available in the interface by using Aspecting.
@see https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

 There are others interfaces that our repositories can extend from, like JPARepositories.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
