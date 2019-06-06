package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Attendee;

import java.util.List;

public interface AssignmentService {

  /**
   * @param id
   * @return
   */

  List<Attendee> getAllAttendeesByAgId(long id);

  /**
   * @param attendeeId
   * @return
   */

  Attendee getAttendeeById(Long attendeeId);

}
