package de.schlossgaienhofen.project2019.controller;


import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.ActivityGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Create HomeController Mapping index.html
 */
@Controller
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
   
  private final ActivityGroupService activityGroupService;

  public HomeController(ActivityGroupService activityGroupService) {
    this.activityGroupService = activityGroupService;
  }
  
 

  /**
   * Shows inital home page.
   *
   * @param model
   * @return
   */
  @GetMapping(value = "/")
  public String viewHome(Map<String, Object> model) {
    LOGGER.debug("----->viewHome");
    
	  List<ActivityGroup> allActivityGroups = activityGroupService.getAllActivityGroups();
    model.put("allActivities", allActivityGroups);
    
    LOGGER.debug("<-----viewHome");
    return "index";
  } 
    
  
  @GetMapping(value = "/ag")
  public ModelAndView displayAg(ModelAndView modelAndView,Map<String, Object> model) {
	List<ActivityGroup> allActivityGroups = activityGroupService.getAllActivityGroups();
	model.put("allActivities", allActivityGroups);
    modelAndView.setViewName("ag");
    return modelAndView;
    
  } 
  
}
