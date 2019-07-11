package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.EventUser;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @After
  public void afterTest() {
    userRepository.deleteAll();
  }

  private EventUser userObject() {
    EventUser user = new EventUser();
    user.setEmail("test@test.com");
    user.setUserName("test");
    user.setName("testName");
    user.setFirstName("testFirstName");
    user.setPassword("testPW");
    return user;
  }

  @Test
  public void addNewUser() {
    userService.addNewUser(userObject());
    EventUser user = userService.findUserByUserName("test");
    Assert.assertEquals("testName", user.getName());
  }

}
