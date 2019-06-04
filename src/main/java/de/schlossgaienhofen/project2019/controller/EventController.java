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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class EventController extends UserManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventService eventService;

  /**
   * List all ActivityGroups.
   *
   * @param modelAndView
   * @param model
   * @return
   */
  @GetMapping(value = "/ag")
  public ModelAndView all(ModelAndView modelAndView, Map<String, Object> model) {
    List<Event> allEvents = eventService.getAllEvents();

    model.put("allActivitiesList", allEvents);

    modelAndView.setViewName("schuelerAg");

    return modelAndView;
  }

  /**
   * Get detail page of specific Event by given id.
   *
   * @param id    ID of the Event
   * @param model
   * @return
   */
  @GetMapping(value = "/ag/{id}")
  public String get(@PathVariable(name = "id") Long id, Map<String, Object> model) {
    LOGGER.debug("-> getEventById id={}", id);

    Event event = eventService.getEventById(id);

    if (event != null) {
      User agLeader = event.getLeader();
      model.put("event", event);
      model.put("agLeader", agLeader);
    }

    LOGGER.debug("<- getEventById");
    return "ag-detail";
  }

  /**
   * Assign current authenticated user to given Event.
   *
   * @param id
   * @return
   */
  @PostMapping(value = "/ag/{id}/assign")
  public String assign(@PathVariable(name = "id") Long id) {
    LOGGER.debug("-> assign id={}", id);

    User user = getCurrentUser();
    eventService.assignEventIdWithUser(id, user);

    LOGGER.debug("<- assign");
    return "redirect:/";
  }

  @GetMapping(value = "/ag/create")
  public ModelAndView showForm(ModelAndView modelAndView) {
    modelAndView.addObject("event", new Event());
    modelAndView.setViewName("create");
    return modelAndView;
  }

  @PostMapping(value = "/ag/create")
  public String saveForm(@ModelAttribute Event event, Map<String, Object> model) {
    LOGGER.debug("--> saveForm title={}", event.getTitle());

    event = eventService.create(event);
    model.put("event", event);

    LOGGER.debug("<-- saveForm");
    return "redirect:/ag/" + event.getId();
  }
}
