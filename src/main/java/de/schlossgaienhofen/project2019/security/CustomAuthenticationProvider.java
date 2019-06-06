package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    LOGGER.debug("--> authenticate");
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    User existingUser = getExistingUser(email);

    UsernamePasswordAuthenticationToken result = null;
    try {
      result = getUserNamePasswordAuthenticationToken(existingUser, email, password);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("NoSuchAlgorithmException " + e);
    }
    LOGGER.debug("<-- authenticate");
    return result;
  }

  private UsernamePasswordAuthenticationToken getUserNamePasswordAuthenticationToken(User existingUser, String email, String password) throws NoSuchAlgorithmException {
    LOGGER.debug("--> getUserNamePasswordAuthenticationToken");
    String encryptedPassword = userService.getSHA(password);
    UsernamePasswordAuthenticationToken result = null;
    if (existingUser != null && existingUser.getPassword().equals(encryptedPassword)) {
      result = new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
    }
    LOGGER.debug("<-- getUserNamePasswordAuthenticationToken");
    return result;
  }


  private User getExistingUser(String email) {
    LOGGER.debug("--> getExistingUser");
    User user;
    try {
      user = userService.findUserByEmail(email);
    } catch (IllegalArgumentException iae) {
      LOGGER.error("getExistingUser: UserReturnValue null: " + iae);
      user = null;
    }
    LOGGER.debug("<-- getExistingUser");
    return user;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    LOGGER.debug("--> supports");
    LOGGER.debug("<-- supports");
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
