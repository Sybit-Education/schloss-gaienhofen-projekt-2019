package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Course;
import de.schlossgaienhofen.project2019.repository.CourseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

  @Autowired
  CourseService courseService;

  @Autowired
  CourseRepository courseRepository;

  @Test
  public void getAllCourse() {
    List<Course> allCourses = courseService.getAllCourses();
    Assert.assertEquals(10, allCourses.size());
  }

}
