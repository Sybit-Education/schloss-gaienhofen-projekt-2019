package de.schlossgaienhofen.project2019.controller;


import de.schlossgaienhofen.project2019.entity.Course;
import de.schlossgaienhofen.project2019.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Create HomeController Mapping index.html
 */
@Controller
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  private final CourseService courseService;

  public HomeController(CourseService courseService) {
    this.courseService = courseService;
  }

  /**
   * Shows all WeeklyReports on index
   *
   * @param model
   * @return
   */
  @GetMapping(value = "/")
  public String viewHome(Map<String, Object> model) {
    LOGGER.debug("--> viewHome");
    List<Course> allCourseList = courseService.getAllCourses();
    model.put("allCourse", allCourseList);
    LOGGER.debug("<-- viewHome");
    return "index";
  }
}
