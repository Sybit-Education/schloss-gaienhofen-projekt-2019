package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
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

    List<Event> events = eventService.getAllActivityGroups();

    Assert.assertNotNull(events);
    Assert.assertTrue(events.size() > 2);
    Assert.assertNotNull(events.get(0));
  }

  @Test
  public void testCreateActivityGroup() {
    Event ag = new Event();
    ag.setTitle("Titel");
    ag.setLeaderId("Leader");

    int sizeBefore = eventService.getAllActivityGroups().size();

    Assert.assertNull(ag.getId());
    ag = eventService.create(ag);

    Assert.assertNotNull(ag);
    Assert.assertNotNull(ag.getId());

    int sizeAfter = eventService.getAllActivityGroups().size();

    Assert.assertEquals(sizeBefore + 1, sizeAfter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateActivityGroup_withId() {
    Event ag = new Event();
    ag.setId(12L);
    ag.setTitle("Titel");
    ag.setLeaderId("Leader");
    eventService.create(ag);
  }

  @Test
  public void testGetAllActivityGroupsOfUser() {
    User user = new User();
    user.setId(1L);

    List<Event> events = eventService.getActivityGroupsOfUser(user);

    Assert.assertNotNull(events);
    Assert.assertEquals(1, events.size());
  }
}
