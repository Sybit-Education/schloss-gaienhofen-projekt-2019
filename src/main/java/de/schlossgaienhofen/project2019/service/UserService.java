package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;

import javax.validation.constraints.NotEmpty;
import java.security.NoSuchAlgorithmException;

public interface UserService {

  /**
   * Saves a User to DB
   *
   * @param user
   */

  User addNewUser(User user);

  /**
   * Returns a userObject by given email
   *
   * @param email
   * @return user
   */

  User findUserByEmail(@NotEmpty String email);

  /**
   * Update or create given user.
   *
   * @param loggedInUser
   * @return
   */
  User update(User loggedInUser);
}
