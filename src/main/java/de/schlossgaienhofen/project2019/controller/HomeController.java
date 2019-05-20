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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create HomeController Mapping index.html
 */
@Controller
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private ActivityGroupService activityGroupService;

  @Autowired
  private UserService userService;

  /**
   * Shows initial home page.
   *
   * @param model
   * @return
   */
  @GetMapping(value = "/")
  public String viewHome(Map<String, Object> model) {
    LOGGER.debug("-> viewHome");
    List<ActivityGroup> allActivityGroupsActive = activityGroupService.getAllActiveActivityGroups();
    model.put("allActivityGroupsActive", allActivityGroupsActive);
    List<ActivityGroup> allActivityGroupsInactive = activityGroupService.getAllInactiveActivityGroups();
    model.put("allActivityGroupsInactive", allActivityGroupsInactive);
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    User user = this.userService.findUserByEmail(authentication.getName());

    Map<Long, Boolean> assignment = new HashMap<>();
    for (ActivityGroup next : allActivityGroupsActive) {
      if (activityGroupService.isAssigned(user, next)) {
        assignment.put(next.getId(), true);
      } else {
        assignment.put(next.getId(), false);
      }
    }
    model.put("assignments", assignment);
    LOGGER.debug("<- viewHome");
    return "index";
  }

}
