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
  
   /**
    * Get detail page of specific ActivityGroup by given id.
    * 
    * @param id ID of the ActivityGroup
    * @param model
    * @return 
    */
  @GetMapping(value = "/ag")
  public String get(@RequestParam(name = "id") Long id, Map<String, Object> model) {
    LOGGER.debug("-> get id={}", id);
    
    ActivityGroup ag = activityGroupService.get(id);
    model.put("ag", ag);
    
    LOGGER.debug("<- get");
    return "ag-detail";
  }
}
