package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.security.UserManager;
import de.schlossgaienhofen.project2019.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create HomeController Mapping index.html
 */
@Controller
public class HomeController extends UserManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private EventService eventService;

  /**
   * Shows initial home page.
   *
   * @param model
   * @return
   */
  @GetMapping(value = "/")
  public String viewHome(Map<String, Object> model) {
    LOGGER.debug("-> viewHome");
    List<Event> allEventsActiveList = eventService.getAllEvents();
    model.put("allEventsActive", allEventsActiveList);

    User user = getCurrentUser();

    Map<Long, Boolean> assignment = new HashMap<>();
    for (Event eachEvent : allEventsActiveList) {
      if (eventService.isUserAssignedWithEvent(user, eachEvent)) {
        assignment.put(eachEvent.getId(), true);
      } else {
        assignment.put(eachEvent.getId(), false);
      }
    }
    model.put("assignments", assignment);
    LOGGER.debug("<- viewHome");
    return "index";
  }

}
