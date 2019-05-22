package de.schlossgaienhofen.project2019.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * The different roles users can have in the application.
 *
 * @author Stephan Strittmatter
 * @version 1.0
 */
public enum Authority implements GrantedAuthority {
   /* Role of Administrator */
   ROLE_TEACHER,
   /* Role of common Azubi */
   ROLE_SECRETARY,
   ROLE_PUPIL;


   /**
    * Get the String-representation of the autority.
    *
    * @return String of role.
    * @see org.springframework.security.core.GrantedAuthority#getAuthority()
    */
   @Override
   public String getAuthority() {
      return name();
   }

}
