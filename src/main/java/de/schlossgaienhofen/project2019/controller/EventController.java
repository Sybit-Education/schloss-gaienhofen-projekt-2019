package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.data.SelectOption;
import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.EventService;
import de.schlossgaienhofen.project2019.service.StateService;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class EventController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventService eventService;

  @Autowired
  private UserService userService;

  @Autowired
  private StateService stateService;

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
  @PostMapping(value = "/ag/{id}/assign")
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

  @GetMapping(value = "/ag/create")
  public ModelAndView showForm(Map<String, Object> model, Event event) {

    Map<String, Object> stringObjectMap = mapStateToModel(model, event);

    return new ModelAndView("create", stringObjectMap);
  }

  @PostMapping(value = "/ag/create")
  public String saveForm(@ModelAttribute Event event, Map<String, Object> model) {
    LOGGER.debug("--> saveForm title={}", event.getTitle());

    event = eventService.create(event);
    model.put("event", event);

    mapStateToModel(model, event);

    LOGGER.debug("<-- saveForm");
    return "redirect:/ag/" + event.getId();
  }

  private Map<String, Object> mapStateToModel(Map<String, Object> model, Event event) {

    LOGGER.debug("--> mapStateToModel");

    LOGGER.trace("event = {}", event);
    model.put("event", event);

    List<SelectOption> selectStateList = stateService.getSelectOptionFactory(event);
    model.put("allStates", selectStateList);

    LOGGER.debug("<-- mapStateToModel");

    return model;
  }

}
