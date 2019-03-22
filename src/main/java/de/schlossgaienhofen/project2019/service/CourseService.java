package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Course;
import de.schlossgaienhofen.project2019.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);


  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  //TODO: Remove when Flyway is integrated
  private List<Course> demoCourse() {
    List<Course> demoCourse = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Course course = new Course();
      course.setCourseName("Course Nr. " + i);
      demoCourse.add(i, course);
    }
    return demoCourse;
  }


  /**
   * Gets all Courses and returns them
   *
   * @return
   */

  public List<Course> getAllCourses() {
    LOGGER.debug("--> getAllCourses");
    List<Course> allCourseList = courseRepository.findAll();
    //TODO: Remove following line when Flyway is integrated
    allCourseList = demoCourse();
    LOGGER.debug("<-- getAllCourses");
    return allCourseList;
  }

}
