package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Attendee;
import de.schlossgaienhofen.project2019.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService{

  @Autowired
  private AttendeeRepository attendeeRepository;

  @Override
  public List<Attendee> getAllattendeesbyAgId (long id){

    List<Attendee> allAttendees = attendeeRepository.findByEventId(id);
    return allAttendees;
  }

  @Override
  public Attendee getAttendee(Long id){

    if (id == null){
      throw new IllegalArgumentException("Id can not be null");
    }

    Attendee attendee = attendeeRepository.findByAttendeeId(id);

    return attendee;

  }

}
