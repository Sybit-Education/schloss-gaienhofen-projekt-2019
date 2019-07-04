package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

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

  private User userObject() {
    User user = new User();
    user.setEmail("test@test.com");
    user.setName("testName");
    user.setFirstName("testFirstName");
    user.setPassword("testPW");
    return user;
  }

  @Test
  public void addNewUser() {
    userService.addNewUser(userObject());
    User user = userService.findUserByEmail("test@test.com");
    Assert.assertEquals("testName", user.getName());
  }

}
