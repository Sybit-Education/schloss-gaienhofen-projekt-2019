package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Attendee;
import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.AttendeeRepository;
import de.schlossgaienhofen.project2019.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private AttendeeRepository attendeeRepository;

  @Override
  public List<Event> getAllEvents() {
    LOGGER.debug("-> getAllEvents");
    List<Event> allEvents = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));

    LOGGER.debug("<- getAllEvents size={}", allEvents.size());
    return allEvents;
  }

  @Override
  public List<Event> getAllActiveEvents() {
    LOGGER.debug("-> getAllEvents");
    List<Event> allEvents = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
    List<Event> allActiveEvents = new ArrayList<>();

    for (Event event : allEvents) {
      if (event.getEventState().equals("online")) {
        allActiveEvents.add(event);
      }

    }
    LOGGER.debug("<- getAllEvents size={}", allEvents.size());
    return allActiveEvents;
  }

  @Override
  public List<Event> getAllInactiveEvents() {
    LOGGER.debug("-> getAllEvents");
    List<Event> allEvents = eventRepository.findAll(Sort.by("title"));
    List<Event> allInactiveEvents = new ArrayList<>();

    for (Event event : allEvents) {
      if (event.getEventState().equals("offline")) {
        allInactiveEvents.add(event);
      }

    }
    LOGGER.debug("<- getAllEvents size={}", allEvents.size());
    return allInactiveEvents;
  }

  @Override
  public List<Event> getEventsOfUser(User user) {
    LOGGER.debug("-> getEventsOfUser user={}", user.getEmail());
    List<Event> allEvents = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));

    List<Event> myEvents = new ArrayList<>();

    for (Event event : allEvents) {
      if (isUserAssignedWithEvent(user, event)) {
        myEvents.add(event);
      }
    }

    LOGGER.debug("<- getEventsOfUser size={}", myEvents.size());
    return myEvents;
  }

  @Override
  public Event getEventById(Long id) {
    LOGGER.debug("-> getEventById id={}", id);
    Event event = null;
    Optional<Event> eventOptional = eventRepository.findById(id);
    if (eventOptional.isPresent()) {
      event = eventOptional.get();
    }
    LOGGER.debug("<- getEventById");
    return event;
  }

  @Override
  public Event updateEvent(Event event) throws IllegalArgumentException {
    Event updatedEvent;
    if (event.getId() != null) {
      updatedEvent = eventRepository.save(event);
    } else {
      throw new IllegalArgumentException("Event doesn't have an id");
    }
    return updatedEvent;
  }

  @Override
  @Transactional
  public void deleteEventById(Long id) {
    Event event = getEventById(id);
    eventRepository.delete(event);
  }

  @Override
  public Event assignEventIdWithUser(Long eventId, User user) {
    LOGGER.debug("-> assignEventIdWithUser id={} user={}", eventId, user.getEmail());

    Event event = getEventById(eventId);
    if (event == null) {
      throw new IllegalArgumentException("Couldn't fetch event data properly");
    }
    Attendee attendee = attendeeRepository.findByEventAndAttendee(event, user);
    if (attendee == null) {

      attendee = new Attendee();
      attendee.setEvent(event);
      attendee.setAttendee(user);
      attendee.setAssignmentDate(LocalDate.now());
    }

    attendeeRepository.saveAndFlush(attendee);
    LOGGER.debug("<- assignEventIdWithUser");
    return event;
  }

  @Override
  public boolean isUserAssignedWithEvent(User user, Event event) {
    Attendee attendee = attendeeRepository.findByEventAndAttendee(event, user);
    return attendee != null;
  }

  @Override
  public Event saveEvent(@NotNull Event event) {
    if (event != null && event.getId() != null) {
      throw new IllegalArgumentException("Newly created object does not have an id.");
    }
    assert event != null;
    return eventRepository.saveAndFlush(event);
  }
}
