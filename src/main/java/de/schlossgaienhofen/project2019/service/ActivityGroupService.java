package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ActivityGroupService {

  List<ActivityGroup> getAllActivityGroups();

  List<ActivityGroup> getAllActiveActivityGroups();

  List<ActivityGroup> getAllInactiveActivityGroups();

  /**
   * Get List of ActivityGroups which are assigned to given user.
   *
   * @param user
   * @return List of assigned ActivityGroups
   */

  List<ActivityGroup> getActivityGroupsOfUser(User user);

  ActivityGroup get(Long id);

  /**
   * @param id
   * @param user
   * @return
   */

  ActivityGroup assignUser(Long id, User user);

  boolean isAssigned(User user, ActivityGroup ag);

  ActivityGroup create(@NotNull ActivityGroup activityGroup);
}
