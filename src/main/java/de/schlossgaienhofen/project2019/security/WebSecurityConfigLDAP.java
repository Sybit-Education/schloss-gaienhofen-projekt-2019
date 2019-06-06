package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.service.EventServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("development")
@Configuration
public class WebSecurityConfigLDAP extends WebSecurityConfigurerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfigurerAdapter.class);
  public static final String PASSWORD_ATTRIBUTE_NAME = "userPassword";

  @Value("${ldap.domain:schloss-gaienhofen.email}")
  private String domain;

  @Value("${ldap.url:ldap://localhost}")
  private String url;

  @Value("${spring.ldap.embedded.port:389}")
  private int port;

  @Value("${ldap.root:dc=schloss-gaienhofen,dc=email}")
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

  @SuppressWarnings("deprecation")
  @Override
  protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

    LOGGER.debug("domain={}, url={}, port= {}, root={}", domain, url, port, root);
    /*
    ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
      new ActiveDirectoryLdapAuthenticationProvider(domain, url + ":" + port);
    authenticationProvider.setAuthoritiesMapper(new AuthoritiesMapper());

    authManagerBuilder
      .ldapAuthentication()
      .userDnPatterns("mail={0},ou=people")
      .groupSearchBase("ou=groups")
      .contextSource()
      .url(url).port(port).root(root)
      .and()
      .passwordCompare().passwordEncoder(new LdapShaPasswordEncoder()).passwordAttribute("userPassword")
      .and()
      .authoritiesMapper(new AuthoritiesMapper());
*/

/*
    domain = "";
    ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
      new ActiveDirectoryLdapAuthenticationProvider(domain, url + ":" + port);
    authenticationProvider.setUserDetailsContextMapper(new InetOrgPersonContextMapper());
    authenticationProvider.setAuthoritiesMapper(new AuthoritiesMapper());
*/
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    authManagerBuilder.ldapAuthentication()
      .userSearchBase("dc=schloss-gaienhofen,dc=email")
      .userSearchFilter("(&(ou=people)(mail={0}))")
      .groupSearchBase("ou=groups")
      .contextSource()
      .url("ldap://127.0.0.1:" + port )
      .ldif("classpath:test-schema.ldif")
      .and()
      .passwordCompare().passwordEncoder(passwordEncoder).passwordAttribute(PASSWORD_ATTRIBUTE_NAME)
      .and()
      .userDetailsContextMapper(getUserDetailsContextMapper()).authoritiesMapper(new AuthoritiesMapper());
      /*.and()
      .authenticationProvider(authenticationProvider);*/

  }

  @Bean
  public LdapUserDetailsMapper getUserDetailsContextMapper() {
    LdapUserDetailsMapper userDetailsMapper = new LdapUserDetailsMapper();
    userDetailsMapper.setPasswordAttributeName(PASSWORD_ATTRIBUTE_NAME);
    return userDetailsMapper;
  }

}

