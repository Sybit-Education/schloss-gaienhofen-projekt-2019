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

  void addNewUser(User user);

  /**
   * Returns a userObject by given email
   *
   * @param email
   * @return user
   */

  User findUserByEmail(@NotEmpty String email);

  /**
   * Hashes password using sha256 by given password
   *
   * @param input
   * @return
   */

  String getSHA(String input) throws NoSuchAlgorithmException;

  /**
   * Returns a userObject by given id
   *
   * @param id
   * @return user
   */

  User findUserById(@NotEmpty long id);
}
