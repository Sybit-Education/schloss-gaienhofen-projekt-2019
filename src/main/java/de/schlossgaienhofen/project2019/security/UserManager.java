package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.exception.UserAuthenticationFailed;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;

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

  public User getCurrentUserExtend() {
    LOGGER.debug("--> getCurrentUserExtend");
    final SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String email;
    if (authentication.getPrincipal() instanceof InetOrgPerson) {
      //productive ActiveDirectory
      InetOrgPerson principal = (InetOrgPerson) authentication.getPrincipal();
      email = principal.getUsername();
    } else if (authentication.getPrincipal() instanceof LdapUserDetailsImpl) {
      //local file based LDAP
      LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();
      email = principal.getUsername();
    } else {
      throw new UserAuthenticationFailed("Unhandled principal type");
    }
    LOGGER.debug("<-- getCurrentUserExtend");
    return userService.findUserByEmail(email);
  }

}
