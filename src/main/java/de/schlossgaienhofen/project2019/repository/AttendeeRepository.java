package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.Attendee;
import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

  Attendee findByEventAndAttendee(Event event, User user);

  List<Attendee> findByEventId(Long id);

  void deleteByAttendeeIdAndEventId(Long attendeeId, Long eventId);
}
