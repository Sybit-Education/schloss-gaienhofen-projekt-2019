package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class UserManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);

  @Autowired
  private UserService userService;

  protected User getCurrentUser() {
    LOGGER.debug("--> getCurrentUser");
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    User user = userService.findUserByEmail(authentication.getName());
    LOGGER.debug("<-- getCurrentUser");
    return user;
  }

}
