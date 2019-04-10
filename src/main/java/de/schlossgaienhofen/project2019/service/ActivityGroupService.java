/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.Attendee;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.ActivityGroupRepository;
import de.schlossgaienhofen.project2019.repository.AttendeeRepository;
import java.time.LocalDate;
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
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ActivityGroupService.class);

  private final ActivityGroupRepository activityGroupRepository;
  private final AttendeeRepository attendeeRepository;

  public ActivityGroupService(ActivityGroupRepository activityGroupRepository, AttendeeRepository attendeeRepository) {
    this.activityGroupRepository = activityGroupRepository;
    this.attendeeRepository = attendeeRepository;
  }

  public List<ActivityGroup> getAllActivityGroups(){
      List<ActivityGroup> allActivityGroups = activityGroupRepository.findAll();

      return allActivityGroups;
  }

  public ActivityGroup get(Long id) {
    return activityGroupRepository.getOne(id);
  }

  /**
   * 
   * @param id
   * @param user
   * @return 
   */
  public ActivityGroup assignUser(Long id, User user) {
    LOGGER.debug("--> assignUser id={} user={}", id, user.getEmail());
    
    ActivityGroup ag = this.get(id);
    
        
    Attendee attendee = attendeeRepository.findByIdAndAttendeeId(id, user.getId());
    if(attendee == null) {
      
      attendee = new Attendee();
      attendee.setActivityGroup(ag);
      attendee.setAttendee(user);
      attendee.setAssignemtDate(LocalDate.now());

      if(!ag.getAttendees().contains(attendee)) {
        LOGGER.debug("add attendee");
        ag.getAttendees().add(attendee);
      }
    }

    ag = activityGroupRepository.saveAndFlush(ag);
    attendeeRepository.saveAndFlush(attendee);
    
    LOGGER.debug("<-- assignUser");
    return ag;
  }
  
  
  public boolean isAssigned(User user, ActivityGroup ag) {
    
     Attendee attendee = attendeeRepository.findByIdAndAttendeeId(ag.getId(), user.getId());
     
     if (attendee == null) {
       return false;
     } else {
       return true;
     }
    
  }

  public ActivityGroup save(ActivityGroup activityGroup) {
    return this.activityGroupRepository.saveAndFlush(activityGroup);
  }
}
