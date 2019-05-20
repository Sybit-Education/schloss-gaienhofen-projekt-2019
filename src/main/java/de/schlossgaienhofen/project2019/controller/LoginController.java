package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

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
  public ModelAndView displayRegister(ModelAndView modelAndView, User user) {
    modelAndView.addObject("user", user);
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @PostMapping(value = "/register")
  public ModelAndView registerUser(ModelAndView modelAndView, User user) {

    LOGGER.debug("--> register");
    modelAndView.setViewName("register");
    if (userRepository.findByEmail(user.getEmail()) != null) {
      modelAndView.addObject("message", "This email already exists!");
      modelAndView.setViewName("error");
    }
    userService.addNewUser(user);

    modelAndView.setViewName("login");

    LOGGER.debug("<-- register");
    return modelAndView;
  }

}
