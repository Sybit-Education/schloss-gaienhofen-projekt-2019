package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

  private final UserRepository userRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Saves a User to DB
   *
   * @param firstName, name, email
   */

  public void addNewUser(String firstName, String name, String email, String plainPassword) {
    LOGGER.debug("--> addNewUser");
    User newUser = createUserObject(firstName, name, email, plainPassword);
    userRepository.save(newUser);
    LOGGER.debug("<-- addNewUser");
  }

  /**
   * Creates a new UserObject by an given firstName, name and email
   *
   * @param firstName, name, email
   * @return user
   */

  private User createUserObject(@NotEmpty String firstName, @NotEmpty String name, @NotEmpty String email,
                                @NotEmpty String password) {
    LOGGER.debug("--> createUserObject");
    User user = new User();
    user.setFirstName(firstName);
    user.setName(name);

    user.setEmail(emailValidator(email));

    try {
      user.setPassword(getSHA(password));
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("Error getSha" + e);
    }
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

  /**
   * Hashes password using sha256 by given password
   *
   * @param input
   * @return
   */

  String getSHA(String input) throws NoSuchAlgorithmException {
    LOGGER.debug("--> getSHA");
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
