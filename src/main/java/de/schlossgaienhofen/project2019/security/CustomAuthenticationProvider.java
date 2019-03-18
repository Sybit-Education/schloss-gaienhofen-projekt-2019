
package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private final UserService userService;

  public CustomAuthenticationProvider(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication)
    throws AuthenticationException {

    String email = authentication.getName();
    String password = authentication.getCredentials().toString();
    User existingUser = userService.findUserByEmail(email);
    UsernamePasswordAuthenticationToken result = null;

    try {
      if (existingUser != null && existingUser.getPassword().equals(userService.getSHA(password))) {
        result = new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
      }
    } catch (NoSuchAlgorithmException ex) {
      LOGGER.error("authenticate error" + ex);
    }
    return result;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
