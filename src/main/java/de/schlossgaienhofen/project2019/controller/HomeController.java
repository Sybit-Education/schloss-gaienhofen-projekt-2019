package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.ActivityGroupService;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Create HomeController Mapping index.html
 */
@Controller
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  private final ActivityGroupService activityGroupService;
  private final UserService userService;

  public HomeController(ActivityGroupService activityGroupService, UserService userService) {
    this.activityGroupService = activityGroupService;
    this.userService = userService;
  }

  /**
   * Shows inital home page.
   *
   * @param model
   * @return
   */
  @GetMapping(value = "/")
  public String viewHome(Map<String, Object> model) {
    LOGGER.debug("-> viewHome");

    List<ActivityGroup> allActivityGroups = activityGroupService.getAllActivityGroups();
    model.put("allActivities", allActivityGroups);

    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    User user = this.userService.findUserByEmail(authentication.getName());

    Map<Long, Boolean> assinments = new HashMap();
    for (Iterator<ActivityGroup> iterator = allActivityGroups.iterator(); iterator.hasNext(); ) {
      ActivityGroup next = iterator.next();

      if (activityGroupService.isAssigned(user, next)) {
        assinments.put(next.getId(), true);
      } else {
        assinments.put(next.getId(), false);
      }
    }
    model.put("assignments", assinments);

    LOGGER.debug("<- viewHome");
    return "index";
  }

}
