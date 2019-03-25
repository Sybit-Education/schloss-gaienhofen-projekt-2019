/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.repository.ActivityGroupRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author cwr
 */
@Service
public class ActivityGroupService {
    
    
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

  

  private final ActivityGroupRepository activityGroupRepository;

  public ActivityGroupService(ActivityGroupRepository activityGroupRepository) {
    this.activityGroupRepository = activityGroupRepository;
  }
  
  public List<ActivityGroup> getAllActivityGroups(){
      List<ActivityGroup> allActivityGroups = activityGroupRepository.findAll();
      
      return allActivityGroups;
  }
    
}
