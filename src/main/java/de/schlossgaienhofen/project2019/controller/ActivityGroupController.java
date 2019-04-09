/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.service.ActivityGroupService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ssr
 */
@Controller
public class ActivityGroupController {
  
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityGroupController.class);
   
  private final ActivityGroupService activityGroupService;

   public ActivityGroupController(ActivityGroupService activityGroupService) {
    this.activityGroupService = activityGroupService;
  }
   
  @GetMapping(value = "/ag")
  public ModelAndView all(ModelAndView modelAndView, Map<String, Object> model) {
    List<ActivityGroup> allActivityGroups = activityGroupService.getAllActivityGroups();
    model.put("allActivities", allActivityGroups);
    modelAndView.setViewName("ag");
    return modelAndView;

  }
  
  @GetMapping(value = "/ag/detail")
  public String get(@RequestParam(name = "id") Long id, Map<String, Object> model) {
    LOGGER.debug("-> get id={}", id);
    
    ActivityGroup ag = activityGroupService.get(id);
    model.put("ag", ag);
    
    LOGGER.debug("<- get");
    return "ag";
  }
  
  @GetMapping(value = "/ag/create")
  public ModelAndView displayRegister(ModelAndView modelAndView) {
    modelAndView.setViewName("create");
    return modelAndView;
  }
}
