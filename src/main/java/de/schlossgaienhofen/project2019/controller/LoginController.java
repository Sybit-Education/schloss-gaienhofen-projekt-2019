package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  private final UserService userService;
  private final UserRepository userRepository;

  public LoginController(UserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
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
  public ModelAndView displayRegister(ModelAndView modelAndView, User user)  {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
  }

  @PostMapping(value = "/register")
  public ModelAndView registerUser(ModelAndView modelAndView, User user) {

    LOGGER.debug("--> register");
    
    if(userRepository.findByEmail(user.getEmail()) != null){
        modelAndView.addObject("message","This email already exists!");
        modelAndView.setViewName("error");
    }
    userService.addNewUser(user);

    LOGGER.debug("<-- register");
    return modelAndView;
  }

}
