package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Collection;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

   private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

   private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

   @Autowired
   private UserService userService;


   /**
    * Handler calls the determineTargetUrl method and returns the redirect strategy
    *
    * @param request
    * @param response
    * @param authentication
    * @throws IOException
    */

   @Override
   protected void handle(HttpServletRequest request,
                         HttpServletResponse response, Authentication authentication)
      throws IOException {

      String targetUrl = determineTargetUrl(authentication);

      if (response.isCommitted()) {
         LOGGER.debug(
            "Response has already been committed. Unable to redirect to "
               + targetUrl);
         return;
      }

      redirectStrategy.sendRedirect(request, response, targetUrl);
   }

   /**
    * Decides whether the given user has the right admin or azubi
    *
    * @param authentication
    * @return
    */

   private String determineTargetUrl(Authentication authentication) {
      String targetUrl;

      User currentUser = convert2User(authentication);
      User existingUser = userService.findUserByEmail(currentUser.getEmail());

      Collection<? extends GrantedAuthority> authorities
         = authentication.getAuthorities();

      if (true)  { //TODO: check role
         targetUrl = "/";

      } else {
         SecurityContextHolder.clearContext();
         targetUrl = "/login";
      }

      return targetUrl;
   }

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
      LOGGER.debug("--> onAuthenticationSuccess");


      super.onAuthenticationSuccess(request, response, authentication);

      LOGGER.debug("<-- onAuthenticationSuccess");
   }

   private User convert2User(Authentication authentication) {
      User user = new User();

      if (authentication.getPrincipal() instanceof InetOrgPerson) {
         InetOrgPerson principal = (InetOrgPerson) authentication.getPrincipal();
         String[] fullNameCut = principal.getDisplayName().split(",");
         String subFirst = fullNameCut[1];
         String subLast = fullNameCut[0];

         user.setEmail(principal.getMail());
         user.setFirstName(subFirst);
         user.setName(subLast);
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
