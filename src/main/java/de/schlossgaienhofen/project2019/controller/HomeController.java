package de.schlossgaienhofen.project2019.controller;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create HomeController Mapping index.html
 */
@Controller
public class HomeController {

   private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

   /**
    * Shows all WeeklyReports on index
    *
    * @param model
    * @return
    */
   @GetMapping(value = "/")
   public String viewHome(Map<String, Object> model) {
      LOGGER.debug("--> viewHome");


      LOGGER.debug("<-- viewHome");
      return "index";
   }
}
