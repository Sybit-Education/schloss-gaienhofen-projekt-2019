package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.data.SelectOption;
import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.security.UserManager;
import de.schlossgaienhofen.project2019.service.EventService;
import de.schlossgaienhofen.project2019.service.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/event")
@Controller
public class EventController extends UserManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventService eventService;

  @Autowired
  private StateService stateService;

  /**
   * List viewAll ActivityGroups.
   *
   * @param modelAndView
   * @param model
   * @return
   */
  @GetMapping(value = "/")
  public ModelAndView viewAll(ModelAndView modelAndView, Map<String, Object> model) {
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
  public String getEventById(@PathVariable(name = "id") Long id, Map<String, Object> model) {
    LOGGER.debug("-> getEventById id={}", id);

    Event event = eventService.getEventById(id);

    if (event != null) {
      String agLeader = event.getLeader();
      model.put("event", event);
      model.put("agLeader", agLeader);
    }

    LOGGER.debug("<- getEventById");
    return "event-detail";
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

    User user = getCurrentUser();
    eventService.assignEventIdWithUser(id, user);

    LOGGER.debug("<- assign");
    return "redirect:/";
  }

  @GetMapping(value = "/create")        /** Nur für Lehrer/Sekretariat möglich */
  public ModelAndView showForm(ModelAndView modelAndView, Map<String, Object> model, Event event) {
    modelAndView.addObject("event", new Event());
    modelAndView.setViewName("update_event");
    Map<String, Object> stringObjectMap = mapStateToModel(model, event);

    return new ModelAndView("update_event", stringObjectMap);
    }

  /**
   * creates an event
   *
   * @param event
   * @param model
   * @return
   */
  @PostMapping(value = "/create")             /** Nur für Lehrer/Sekretariat möglich */
  public String saveForm(@ModelAttribute Event event, Map<String, Object> model) {
    LOGGER.debug("--> saveForm title={}", event.getTitle());

    event = eventService.saveEvent(event);
    model.put("event", event);

    mapStateToModel(model, event);

    LOGGER.debug("<-- saveForm");
    return "redirect:/event/" + event.getId();
  }

  @GetMapping(value = "/update/{id}")          /** Nur für Lehrer/Sekretariat möglich */
  public ModelAndView update(@PathVariable(name = "id") Long id, Map<String, Object> model, ModelAndView modelAndView) {
    LOGGER.debug("-> getEventById id={}", id);

    Event event = eventService.getEventById(id);
    model.put("event", event);
    modelAndView.setViewName("update_event");

    LOGGER.debug("<- getEventById");
    Map<String, Object> stringObjectMap = mapStateToModel(model, event);
    return new ModelAndView("update_event", stringObjectMap);
  }

  @PostMapping(value = "/update/{id}")         /** Nur für Lehrer/Sekretariat möglich */
  public String updateEvent(@ModelAttribute Event event, @PathVariable(name = "id") Long id) {
    LOGGER.debug("-> getEventById id={}", id);

    Event oldEvent = eventService.getEventById(id);
    event.setId(oldEvent.getId());
    eventService.updateEvent(event);

    LOGGER.debug("<- getEventById");
    return "redirect:/";
  }

  @GetMapping(value = "/update/{id}/delete")         /** Nur für Lehrer/Sekretariat möglich */
  public String deleteEvent (@PathVariable (name ="id") Long id) {
    eventService.deleteEventById(id);
	  return "redirect:/";
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
