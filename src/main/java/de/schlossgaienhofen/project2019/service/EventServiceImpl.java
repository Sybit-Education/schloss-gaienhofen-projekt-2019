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
    List<Event> allEvents = eventRepository.findAll(Sort.by("title"));

    LOGGER.debug("<- getAllEvents size={}", allEvents.size());
    return allEvents;
  }

  @Override
  public List<Event> getAllActiveEvents() {
    LOGGER.debug("-> getAllEvents");
    List<Event> allEvents = eventRepository.findAll(Sort.by("title"));
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
  public List<Event> getEventsOfUser(User user) {
    LOGGER.debug("-> getEventsOfUser user={}", user.getEmail());
    List<Event> allEvents = eventRepository.findAll(Sort.by("title"));

    List<Event> myEvents = new ArrayList<>();

    for (Event event : allEvents) {
      if (isAssigned(user, event)) {
        myEvents.add(event);
      }
    }

    LOGGER.debug("<- getEventsOfUser size={}", myEvents.size());
    return myEvents;
  }

  @Override
  public Event get(Long id) {
    LOGGER.debug("-> get id={}", id);
    Event event = null;
    Optional<Event> eventOptional = eventRepository.findById(id);
    if (eventOptional.isPresent()) {
      event = eventOptional.get();
    }
    LOGGER.debug("<- get");
    return event;
  }

  @Override
  public Event assignUser(Long id, User user) {
    LOGGER.debug("-> assignUser id={} user={}", id, user.getEmail());

    Event ag = get(id);
    if (ag == null) {
      throw new IllegalArgumentException("Couldn't fetch agData properly");
    }
    Attendee attendee = attendeeRepository.findByIdAndAttendeeId(id, user.getId());
    if (attendee == null) {

      attendee = new Attendee();
      attendee.setEvent(ag);
      attendee.setAttendee(user);
      attendee.setAssignemtDate(LocalDate.now());

      if (!ag.getAttendees().contains(attendee)) {
        LOGGER.debug("add attendee");
        ag.getAttendees().add(attendee);
      }
    }

    ag = eventRepository.saveAndFlush(ag);
    attendeeRepository.saveAndFlush(attendee);

    LOGGER.debug("<- assignUser");
    return ag;
  }

  @Override
  public boolean isAssigned(User user, Event ag) {
    Attendee attendee = attendeeRepository.findByIdAndAttendeeId(ag.getId(), user.getId());
    return attendee != null;
  }

  @Override
  public Event create(@NotNull Event event) {
    if (event.getId() != null) {
      throw new IllegalArgumentException("Newly created object does not have an id.");
    }
    return eventRepository.saveAndFlush(event);
  }
}
