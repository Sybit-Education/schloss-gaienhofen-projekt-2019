package de.schlossgaienhofen.project2019.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

/**
 * This Mapper class maps the user roles to the user after successful authetication.
 * The roles depend on the groups the user is  * member of in the AD.
 *
 * @author Stephan Strittmatter
 */
public class AuthoritiesMapper implements GrantedAuthoritiesMapper {

   private static final String LDAP_GROUP_TEACHER = "Lehrer-Gruppe";

   private static final Logger LOGGER = LoggerFactory.getLogger(AuthoritiesMapper.class);


   /**
    * @param authorities
    * @return
    * @see org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper#mapAuthorities(Collection)
    */
   @Override
   public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {

      Set<Authority> roles = EnumSet.noneOf(Authority.class);

      for (GrantedAuthority grantedAuthority : authorities) {

         //hint: we have to check both: starting with "ROLE_" and without because of different result
         // when using test-schema.ldif or ActiveDirectory.
         String authority = grantedAuthority.getAuthority();
         LOGGER.debug("Authority: " + authority);

         if (authority.toUpperCase().contains(LDAP_GROUP_TEACHER.toUpperCase())) {
            roles.add(Authority.ROLE_TEACHER);
         }
      }

      return roles;
   }
}
