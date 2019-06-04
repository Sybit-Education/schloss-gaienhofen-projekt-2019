package de.schlossgaienhofen.project2019.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("#{environment.getProperty('domain')}")
  private String domain;

  @Value("#{environment.getProperty('url')}")
  private String url;

  @Value("#{environment.getProperty('port')}")
  private int port;

  @Value("#{environment.getProperty('root')}")
  private String root;

  @Autowired
  private AuthenticationSuccessHandler successHandler;

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
      .csrf().ignoringAntMatchers("/h2/**")
      .and()
      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

    ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
      new ActiveDirectoryLdapAuthenticationProvider(domain, url + ":" + port);
    authenticationProvider.setUserDetailsContextMapper(new InetOrgPersonContextMapper());
    authenticationProvider.setAuthoritiesMapper(new AuthoritiesMapper());

    authManagerBuilder.ldapAuthentication()
      .groupSearchBase("ou=groups")
      .userSearchFilter("(&(objectClass=user)(mail={0}))")
      .contextSource()
      .url(url).port(port).root(root)
      .managerDn("managerDn").managerPassword("managerPassword")
      .and()
      .userDetailsContextMapper(new InetOrgPersonContextMapper()).authoritiesMapper(new AuthoritiesMapper())
      .and()
      .authenticationProvider(authenticationProvider);
  }
}
