package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.EventUser;
import de.schlossgaienhofen.project2019.repository.EventRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceImplTest {

  @Autowired
  EventService eventService;

  @Autowired
  EventRepository eventRepository;

  @Test
  public void testGetAllActivityGroups() {

    List<Event> events = eventService.getAllEvents();

    Assert.assertNotNull(events);
    Assert.assertTrue(events.size() > 2);
    Assert.assertNotNull(events.get(0));
  }

  @Test
  public void testCreateActivityGroup() {
    EventUser leader = new EventUser();
    leader.setId(2L);
    leader.setEmail("test@mail.com");

    Event ag = new Event();
    ag.setTitle("Titel");

    int sizeBefore = eventService.getAllEvents().size();

    Assert.assertNull(ag.getId());
    ag = eventService.saveEvent(ag);

    Assert.assertNotNull(ag);
    Assert.assertNotNull(ag.getId());

    int sizeAfter = eventService.getAllEvents().size();

    Assert.assertEquals(sizeBefore + 1, sizeAfter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateActivityGroup_withId() {
    EventUser leader = new EventUser();
    leader.setId(12L);
    leader.setEmail("test@mail.com");

    Event ag = new Event();
    ag.setId(2L);
    ag.setTitle("Titel");
    eventService.saveEvent(ag);
  }

  @Test
  public void testGetAllActivityGroupsOfUser() {
    EventUser user = new EventUser();
    user.setId(1L);

    List<Event> events = eventService.getEventsOfUser(user);

    Assert.assertNotNull(events);
    Assert.assertEquals(1, events.size());
  }
}
