package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EventService {

  List<Event> getAllEvents();

  List<Event> getAllActiveEvents();

  List<Event> getAllInactiveEvents();

  /**
   * Get List of Events which are assigned to given user.
   *
   * @param user
   * @return List of assigned Events
   */

  List<Event> getEventsOfUser(User user);

  Event getEventById(Long id);

  /**
   * @param id
   * @param user
   * @return
   */

  Event assignEventIdWithUser(Long id, User user);

  boolean isUserAssignedWithEvent(User user, Event ag);

  Event saveEvent(@NotNull Event event);

  Event updateEvent(Event event);

  void deleteEventById(Long id);
}
