package de.schlossgaienhofen.project2019.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile({"production"})
@Configuration
public class WebSecurityConfigActiveDirectory extends WebSecurityConfigurerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfigActiveDirectory.class);


  //@Value("#{environment.getProperty('url')}")
  @Value("${ldap.url}")
  private String url;

  //@Value("#{environment.getProperty('port')}")
  @Value("${ldap.port}")
  private int port;

  //@Value("#{environment.getProperty('managerDN')}")
  @Value("${ldap.managerDn}")
  private String managerDn;

  //@Value("#{environment.getProperty('managerPassword')}")
  @Value("${ldap.managerPassword}")
  private String managerPassword;

  @Value("${ldap.root:dc=schloss-gaienhofen,dc=email}")
  private String root;

  @Autowired
  private AuthenticationSuccessHandler successHandler;

  @Bean
  public AuthoritiesMapper grantedAuthoritiesMapper() {
    return new AuthoritiesMapper();
  }

  @Bean
  public InetOrgPersonContextMapper getUserDetailsContextMapper() {
    return new InetOrgPersonContextMapper();
  }

  @Bean
  public ActiveDirectoryLdapAuthenticationProvider getAuthenticationProvider() {
    ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
      new ActiveDirectoryLdapAuthenticationProvider("", url + ":" + port);
    authenticationProvider.setUserDetailsContextMapper(new InetOrgPersonContextMapper());
    authenticationProvider.setAuthoritiesMapper(new AuthoritiesMapper());

    return authenticationProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/favicon.ico", "/libs/**", "/js/**", "/css/**", "/images/**", "/login", "/error").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .successHandler(successHandler)
      .permitAll()
      .and()
      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

    LOGGER.debug("url={}, port= {}, manager={}", url, port, managerDn);

    authManagerBuilder.ldapAuthentication()
      //.userSearchBase(root)
      .userSearchFilter("(mail={0})")
      //.groupSearchBase("ou=groups")
      .contextSource()
      .url(url).port(port)//.root(root)
      .managerDn(managerDn).managerPassword(managerPassword)
      .and()
      .userDetailsContextMapper(getUserDetailsContextMapper())
      .authoritiesMapper(grantedAuthoritiesMapper())
      .and()
      .authenticationProvider(getAuthenticationProvider());
  }

}
