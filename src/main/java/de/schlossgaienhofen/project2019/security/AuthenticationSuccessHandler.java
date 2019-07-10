package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.EventUser;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

   private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

   private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

   @Autowired
   private UserService userService;

   /**
    * This method will call when the user logs in successful
    *
    * @param request
    * @param response
    * @param authentication
    * @throws IOException
    * @throws ServletException
    */
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                       Authentication authentication) throws IOException, ServletException {
     LOGGER.debug("--> onAuthenticationSuccess authentication={}", authentication);

     final EventUser currentUser = convert2User(authentication);

     LOGGER.debug("Update current logged in user: {}", currentUser);
     userService.update(currentUser);

     super.onAuthenticationSuccess(request, response, authentication);

     LOGGER.debug("<-- onAuthenticationSuccess");
   }

   private EventUser convert2User(Authentication authentication) {
      EventUser user = new EventUser();

      if (authentication.getPrincipal() instanceof InetOrgPerson) {

        LOGGER.debug("AD: authentication={}", authentication);

        InetOrgPerson principal = (InetOrgPerson) authentication.getPrincipal();
        LOGGER.debug("AD: principal={}", principal);

        user.setEmail(principal.getMail());
        user.setFirstName(principal.getGivenName());
        user.setName(principal.getSn());

      } else if (authentication.getPrincipal() instanceof LdapUserDetailsImpl) {

        //should be just in case of testing by using ldap-file `test-schema.ldif`
        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();
        user.setEmail(principal.getUsername());
        user.setName(principal.getUsername());

      } else {
        throw new IllegalArgumentException("Unhandled principal type");
      }

      return user;
   }

   @Override
   public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
      this.redirectStrategy = redirectStrategy;
   }

   @Override
   protected RedirectStrategy getRedirectStrategy() {
      return redirectStrategy;
   }
}
