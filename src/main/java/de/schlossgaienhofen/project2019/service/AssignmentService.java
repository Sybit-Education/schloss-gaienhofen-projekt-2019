package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Attendee;

import java.util.List;

public interface AssignmentService {

    List<Attendee> getAllattendeesbyAgId(long id);

    Attendee getAttendee(Long attendeId);

}
