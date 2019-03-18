package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Mapping for loginPage
   *
   * @return
   */

  @GetMapping
  public String login() {
    LOGGER.debug("--> login");
    LOGGER.debug("<-- login");
    return "login";
  }

  @GetMapping(value = "/register")
  public String register() {
    return "register";
  }

  @PostMapping(value = "/register")
  public String register(@RequestParam("firstName") String firstName,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("confirmPassword") String confirmPassword) {

    LOGGER.debug("--> register");

    userService.addNewUser(firstName, name, email, password);

    LOGGER.debug("<-- register");
    return "login";
  }

}
