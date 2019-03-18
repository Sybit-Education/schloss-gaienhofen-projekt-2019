package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.controller.HomeController;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Service
public class UserService {

  private final UserRepository userRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Saves a User to DB
   *
   * @param firstName, name, email
   */

  public void addNewUser(String firstName, String name, String email) {
    LOGGER.debug("--> addNewUser");
    User newUser = createUserObject(firstName, name, email);
    userRepository.save(newUser);
    LOGGER.debug("<-- addNewUser");
  }

  /**
   * Creates a new UserObject by an given firstName, name and email
   *
   * @param firstName, name, email
   * @return user
   */

  private User createUserObject(@NotEmpty String firstName, @NotEmpty String name, @NotEmpty String email) {
    LOGGER.debug("--> createUserObject");
    User user = new User();
    user.setFirstName(firstName);
    user.setName(name);
    user.setEmail(email);
    LOGGER.debug("<-- createUserObject");
    return user;
  }

  /**
   * Returns a userObject by given email
   *
   * @param email
   * @return user
   */

  public User findUserByEmail(@NotEmpty String email) {
    LOGGER.debug("--> findUserByEmail");
    User user = userRepository.findByEmail(email);
    if (user == null) throw new IllegalArgumentException("User is null");
    LOGGER.debug("<-- findUserByEmail");
    return user;
  }
}
