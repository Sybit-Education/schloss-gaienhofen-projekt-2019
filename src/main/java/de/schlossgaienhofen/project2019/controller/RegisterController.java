package de.schlossgaienhofen.project2019.controller;

import de.schlossgaienhofen.project2019.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @PostMapping
    public String register(@RequestParam("vorName") String firstName,
            @RequestParam("nachName") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        LOGGER.debug("--> register");

        userService.addNewUser(firstName, name, email, password);

        LOGGER.debug("<-- register");
        return "login";
    }

}
