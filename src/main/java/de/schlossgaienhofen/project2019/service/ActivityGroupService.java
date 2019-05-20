package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.Attendee;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.ActivityGroupRepository;
import de.schlossgaienhofen.project2019.repository.AttendeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityGroupService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActivityGroupService.class);

  @Autowired
  private ActivityGroupRepository activityGroupRepository;

  @Autowired
  private AttendeeRepository attendeeRepository;

  public List<ActivityGroup> getAllActivityGroups() {
    LOGGER.debug("-> getAllActivityGroups");
    List<ActivityGroup> allActivityGroups = activityGroupRepository.findAll(Sort.by("title"));

    LOGGER.debug("<- getAllActivityGroups size={}", allActivityGroups.size());
    return allActivityGroups;
  }

  public List<ActivityGroup> getAllActiveActivityGroups() {
    LOGGER.debug("-> getAllActivityGroups");
    List<ActivityGroup> allActivityGroups = activityGroupRepository.findAll(Sort.by("title"));
    List<ActivityGroup> allActiveActivityGroups = new ArrayList<>();

    for (ActivityGroup activityGroup : allActivityGroups) {
      if (activityGroup.getAgState().equals("online")) {
        allActiveActivityGroups.add(activityGroup);
      }

    }

    LOGGER.debug("<- getAllActivityGroups size={}", allActivityGroups.size());
    return allActiveActivityGroups;
  }

  /**
   * Get List of ActivityGroups which are assigned to given user.
   *
   * @param user
   * @return List of assigned ActivityGroups
   */
  public List<ActivityGroup> getActivityGroupsOfUser(User user) {
    LOGGER.debug("-> getActivityGroupsOfUser user={}", user.getEmail());
    List<ActivityGroup> allActivityGroups = activityGroupRepository.findAll(Sort.by("title"));

    List<ActivityGroup> myActivityGroups = new ArrayList<>();

    for (ActivityGroup activityGroup : allActivityGroups) {
      if (isAssigned(user, activityGroup)) {
        myActivityGroups.add(activityGroup);
      }
    }

    LOGGER.debug("<- getActivityGroupsOfUser size={}", myActivityGroups.size());
    return myActivityGroups;
  }

  public ActivityGroup get(Long id) {
    LOGGER.debug("-> get id={}", id);
    ActivityGroup activityGroup = null;
    Optional<ActivityGroup> activityGroupOptional = activityGroupRepository.findById(id);
    if (activityGroupOptional.isPresent()) {
      activityGroup = activityGroupOptional.get();
    }
    LOGGER.debug("<- get");
    return activityGroup;
  }

  /**
   * @param id
   * @param user
   * @return
   */
  public ActivityGroup assignUser(Long id, User user) {
    LOGGER.debug("-> assignUser id={} user={}", id, user.getEmail());

    ActivityGroup ag = get(id);
    if (ag == null) {
      throw new IllegalArgumentException("Couldn't fetch agData properly");
    }
    Attendee attendee = attendeeRepository.findByIdAndAttendeeId(id, user.getId());
    if (attendee == null) {

      attendee = new Attendee();
      attendee.setActivityGroup(ag);
      attendee.setAttendee(user);
      attendee.setAssignemtDate(LocalDate.now());

      if (!ag.getAttendees().contains(attendee)) {
        LOGGER.debug("add attendee");
        ag.getAttendees().add(attendee);
      }
    }

    ag = activityGroupRepository.saveAndFlush(ag);
    attendeeRepository.saveAndFlush(attendee);

    LOGGER.debug("<- assignUser");
    return ag;
  }

  public boolean isAssigned(User user, ActivityGroup ag) {
    Attendee attendee = attendeeRepository.findByIdAndAttendeeId(ag.getId(), user.getId());
    return attendee != null;
  }

  public ActivityGroup create(@NotNull ActivityGroup activityGroup) {
    if(activityGroup.getId() != null) {
      throw new IllegalArgumentException("Newly created object does not have an id.");
    }
    return activityGroupRepository.saveAndFlush(activityGroup);
  }
}
