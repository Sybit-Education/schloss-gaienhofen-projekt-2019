package de.schlossgaienhofen.project2019.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

   private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

   /**
    * Mapping for loginPage
    * @return
    */

   @GetMapping
   public String login() {
      return "login";
   }

}
