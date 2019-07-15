package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.EventUser;

import javax.validation.constraints.NotEmpty;

public interface UserService {

  /**
   * Saves a EventUser to DB
   *
   * @param user
   */

  EventUser addNewUser(EventUser user);

  /**
   * Find EventUser by given username.
   *
   * @param username
   * @return
   */
  public EventUser findUserByUserName(@NotEmpty String username);

  /**
   * Update or create given user.
   *
   * @param loggedInUser
   * @return
   */
  EventUser update(EventUser loggedInUser);
}
