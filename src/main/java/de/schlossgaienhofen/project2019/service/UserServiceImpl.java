package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public User addNewUser(User user) {
    LOGGER.debug("--> addNewUser");

    userRepository.saveAndFlush(user);

    LOGGER.debug("<-- addNewUser");
    return user;
  }

  @Override
  public User findUserByEmail(@NotEmpty String email) {
    LOGGER.debug("--> findUserByEmail");
    User user = userRepository.findByEmail(email);

    LOGGER.debug("<-- findUserByEmail");
    return user;
  }

  @Override
  public User update(@NotNull User loggedInUser) {
    LOGGER.debug("--> update");
    User user = findUserByEmail(loggedInUser.getEmail());
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
