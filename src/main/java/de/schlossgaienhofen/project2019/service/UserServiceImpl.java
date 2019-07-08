package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.EventUser;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public EventUser addNewUser(EventUser user) {
    LOGGER.debug("--> addNewUser");

    userRepository.saveAndFlush(user);

    LOGGER.debug("<-- addNewUser");
    return user;
  }

  @Override
  public EventUser findUserByEmail(@NotEmpty String email) {
    LOGGER.debug("--> findUserByEmail");
    EventUser user = userRepository.findByEmail(email);

    LOGGER.debug("<-- findUserByEmail");
    return user;
  }

  @Override
  public EventUser update(@NotNull EventUser loggedInUser) {
    LOGGER.debug("--> update");
    EventUser user = findUserByEmail(loggedInUser.getEmail());
    if(user != null) {
      //maybe name has changed -> update them
      user.setFirstName(loggedInUser.getFirstName());
      user.setName(loggedInUser.getName());
      user = userRepository.saveAndFlush(user);

    } else {
      user = addNewUser(loggedInUser);
    }

    LOGGER.debug("<-- update");
    return user;
  }

}
