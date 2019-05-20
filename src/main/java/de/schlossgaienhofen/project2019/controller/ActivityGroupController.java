package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.ActivityGroupService;
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
public class ActivityGroupController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActivityGroupController.class);

  @Autowired
  private ActivityGroupService activityGroupService;

  @Autowired
  private UserService userService;

  /**
   * List all ActivityGroups.
   *
   * @param modelAndView
   * @param model
   * @return
   */
  @GetMapping(value = "/ag")
  public ModelAndView all(ModelAndView modelAndView, Map<String, Object> model) {
    List<ActivityGroup> allActivityGroups = activityGroupService.getAllActivityGroups();

    model.put("allActivitiesList", allActivityGroups);

    modelAndView.setViewName("schuelerAg");

    return modelAndView;
  }

  /**
   * Get detail page of specific ActivityGroup by given id.
   *
   * @param id    ID of the ActivityGroup
   * @param model
   * @return
   */
  @GetMapping(value = "/ag/{id}")
  public String get(@PathVariable(name = "id") Long id, Map<String, Object> model) {
    LOGGER.debug("-> get id={}", id);

    ActivityGroup ag = activityGroupService.get(id);

    int agLeaderId = Integer.parseInt(ag.getAgLeader());
   /* 
    String mess = "" + agLeaderId;
    throw new IllegalArgumentException(mess);
    */

    User agLeader = userService.findUserById(1);

    model.put("agLeader", agLeader);
    model.put("ag", ag);

    LOGGER.debug("<- get");
    return "ag-detail";
  }

  /**
   * Assign current authenticated user to given ActivityGroup.
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
    this.activityGroupService.assignUser(id, user);

    LOGGER.debug("<- assign");
    return "redirect:/";
  }

  @GetMapping(value = "/ag/create")
  public ModelAndView showForm(ModelAndView modelAndView) {
    modelAndView.addObject("activityGroup", new ActivityGroup());
    modelAndView.setViewName("create");
    return modelAndView;
  }

  @PostMapping(value = "/ag/create")
  public String saveForm(@ModelAttribute ActivityGroup activityGroup, Map<String, Object> model) {
    LOGGER.debug("--> saveForm title={}", activityGroup.getTitle());

    activityGroup = activityGroupService.create(activityGroup);
    model.put("activityGroup", activityGroup);

    LOGGER.debug("<-- saveForm");
    return "redirect:/ag/" + activityGroup.getId();
  }
}
