package de.schlossgaienhofen.project2019.security;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;

import java.util.Collection;

public class ExtendedInetOrgPersonContextMapper extends InetOrgPersonContextMapper {

  public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                        Collection<? extends GrantedAuthority> authorities) {
    InetOrgPerson.Essence p = new InetOrgPerson.Essence(ctx);

    p.setUsername(username);
    p.setAuthorities(authorities);
    p.setGivenName(ctx.getStringAttribute("givenName"));
    p.setSn(ctx.getStringAttribute("sn"));

    return p.createUserDetails();

  }
}
