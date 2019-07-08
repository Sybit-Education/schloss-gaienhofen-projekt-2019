package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.EventUser;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EventService {

  List<Event> getAllEvents();

  List<Event> getAllActiveEvents();

  List<Event> getAllInactiveEvents();     /** Nur für Lehrer/Sekretariat möglich */

  /**
   * Get List of Events which are assigned to given user.
   *
   * @param user
   * @return List of assigned Events
   */

  List<Event> getEventsOfUser(EventUser user);

  Event getEventById(Long id);

  /**
   * @param id
   * @param user
   * @return
   */

  Event assignEventIdWithUser(Long id, EventUser user);

  void removeUserFromEventId(Long attendeeId);

  boolean isUserAssignedWithEvent(EventUser user, Event ag);

  Event saveEvent(@NotNull Event event);

  Event updateEvent(Event event);

  void deleteEventById(Long id);
}
