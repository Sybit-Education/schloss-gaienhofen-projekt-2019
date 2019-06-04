package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

  Attendee findByIdAndAttendeeId(Long ag, Long user);


}
