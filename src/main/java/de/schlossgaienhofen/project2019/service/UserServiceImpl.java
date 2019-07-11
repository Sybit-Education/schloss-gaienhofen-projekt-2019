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
  public EventUser addNewUser(@NotNull EventUser user) {
    LOGGER.debug("--> addNewUser");

    if(user.getUserName() == null) {
      throw new IllegalArgumentException("userName has to be defined: loggedInUser={}" + user);
    }

    user = userRepository.saveAndFlush(user);

    LOGGER.debug("<-- addNewUser");
    return user;
  }

  @Override
  public EventUser findUserByUserName(@NotEmpty String username) {
    LOGGER.debug("--> findUserByUserName username={}", username);

    EventUser user = userRepository.findByUserName(username);

    LOGGER.debug("<-- findUserByUserName");
    return user;
  }

  @Override
  public EventUser update(@NotNull EventUser loggedInUser) {
    LOGGER.debug("--> update user={}", loggedInUser);

    if(loggedInUser.getUserName() == null) {
      throw new IllegalArgumentException("userName has to be defined: loggedInUser={}" + loggedInUser);
    }

    EventUser user = findUserByUserName(loggedInUser.getUserName());

    if(user != null) {
      //maybe name has changed -> update them
      user.setFirstName(loggedInUser.getFirstName());
      user.setName(loggedInUser.getName());
    } else {
      user = loggedInUser;
    }

    user = userRepository.saveAndFlush(user);

    LOGGER.debug("<-- update");
    return user;
  }

}
