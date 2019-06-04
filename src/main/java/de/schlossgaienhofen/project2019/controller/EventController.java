package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.EventService;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/event")
@Controller
public class EventController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventService eventService;

  @Autowired
  private UserService userService;

  /**
   * List all ActivityGroups.
   *
   * @param modelAndView
   * @param model
   * @return
   */
  @GetMapping(value = "/")
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
  @GetMapping(value = "/{id}")
  public String get(@PathVariable(name = "id") Long id, Map<String, Object> model) {
    LOGGER.debug("-> get id={}", id);

    Event event = eventService.get(id);
    if (event != null) {
      model.put("event", event);
    }


    User agLeader = event.getLeader();

    model.put("agLeader", agLeader);

    LOGGER.debug("<- get");
    return "ag-detail";
  }

  /**
   * Assign current authenticated user to given Event.
   *
   * @param id
   * @return
   */
  @PostMapping(value = "/{id}/assign")
  public String assign(@PathVariable(name = "id") Long id) {
    LOGGER.debug("-> assign id={}", id);

    final SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();

    LOGGER.debug("assign current user= {} to AG with id= {}", authentication.getName(), id);
    User user = this.userService.findUserByEmail(authentication.getName());
    eventService.assignUser(id, user);

    LOGGER.debug("<- assign");
    return "redirect:/";
  }

  @GetMapping(value = "/create")
  public ModelAndView showForm(ModelAndView modelAndView) {
    modelAndView.addObject("event", new Event());
    modelAndView.setViewName("edit_event");
    return modelAndView;
  }

  @PostMapping(value = "/create")
  public String saveForm(@ModelAttribute Event event, Map<String, Object> model) {
    LOGGER.debug("--> saveForm title={}", event.getTitle());

    event = eventService.create(event);
    model.put("event", event);

    LOGGER.debug("<-- saveForm");
    return "redirect:/event/" + event.getId();
  }

  @GetMapping(value = "/edit/{id}")
  public ModelAndView edit(@PathVariable(name = "id") Long id, Map<String, Object> model, ModelAndView modelAndView) {
    LOGGER.debug("-> get id={}", id);

    Event event = eventService.get(id);
    model.put("event", event);
    modelAndView.setViewName("edit_event");

    LOGGER.debug("<- get");
    return modelAndView;
  }
  
  @PostMapping(value = "/edit/{id}")
  public String updateevent(@ModelAttribute Event event, @PathVariable(name="id") Long id) {
    LOGGER.debug("-> get id={}", id);

    Event oldEvent = eventService.get(id);
    event.setId(oldEvent.getId());
    event = eventService.edit(event);

    LOGGER.debug("<- get");
    return "redirect:/";
  }
}
