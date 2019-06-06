package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Attendee;
import de.schlossgaienhofen.project2019.repository.AttendeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentServiceImpl.class);

  @Autowired
  private AttendeeRepository attendeeRepository;

  @Override
  public List<Attendee> getAllAttendeesByAgId(long id) {
    LOGGER.debug("--> getAllAttendeesByAgId");
    LOGGER.debug("<-- getAllAttendeesByAgId");
    return attendeeRepository.findByEventId(id);
  }

  @Override
  public Attendee getAttendeeById(Long id) {
    LOGGER.debug("--> getAttendeeById");
    if (id == null) {
      throw new IllegalArgumentException("Id can not be null");
    }
    LOGGER.debug("<-- getAttendeeById");
    return attendeeRepository.findByAttendeeId(id);
  }

}
