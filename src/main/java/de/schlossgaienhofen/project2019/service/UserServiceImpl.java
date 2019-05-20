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
  public void addNewUser(User user) {
    LOGGER.debug("--> addNewUser");
    User newUser = createUserObject(user);
    userRepository.save(newUser);
    LOGGER.debug("<-- addNewUser");
  }

  /**
   * Creates a new UserObject by an given firstName, name and email
   *
   * @param user
   * @return user
   */

  private User createUserObject(@NotNull User user) {
    LOGGER.debug("--> createUserObject");
    User newUserObject = new User();
    newUserObject.setFirstName(user.getFirstName());
    newUserObject.setName(user.getName());

    newUserObject.setEmail(emailValidator(user.getEmail()));

    try {
      newUserObject.setPassword(getSHA(user.getPassword()));
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("Error getSha" + e);
    }
    LOGGER.debug("<-- createUserObject");
    return newUserObject;
  }

  @Override
  public User findUserByEmail(@NotEmpty String email) {
    LOGGER.debug("--> findUserByEmail");
    User user = userRepository.findByEmail(email);
    if (user == null) throw new IllegalArgumentException("User is null");
    LOGGER.debug("<-- findUserByEmail");
    return user;
  }

  @Override
  public String getSHA(String input) throws NoSuchAlgorithmException {
    LOGGER.debug("--> getSHA");
    if (input == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    byte[] messageDigestByteArray = messageDigest.digest(input.getBytes());
    BigInteger bigInteger = new BigInteger(1, messageDigestByteArray);
    StringBuilder hashText = new StringBuilder(bigInteger.toString(16));
    while (hashText.length() < 32) {
      hashText.insert(0, "0");
    }
    LOGGER.debug("<-- getSHA");
    return hashText.toString();

  }

  @Override
  public User findUserById(@NotEmpty long id) {
    LOGGER.debug("--> findUserById");
    Optional<User> ourUser = userRepository.findById(id);
    User user = new User();
    if (ourUser.isPresent()) {
      user = ourUser.get();
    } else {
      throw new IllegalArgumentException("User is null.");
    }
    LOGGER.debug("<-- findUserById");
    return user;
  }

  /**
   * Validates given Email
   *
   * @param email
   * @return
   */

  private String emailValidator(String email) {
    LOGGER.debug("--> emailValidator");
    boolean valid = EmailValidator.getInstance().isValid(email);
    if (valid) {
      LOGGER.debug("<-- emailValidator");
      return email;
    } else {
      throw new IllegalArgumentException("Email is invalid");
    }
  }
}
