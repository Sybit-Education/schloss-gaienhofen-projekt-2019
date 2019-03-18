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
public class UserServiceTest {

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
    Assert.assertEquals("nameTest", user.getName());
  }

  @Test
  public void getSha() throws NoSuchAlgorithmException {

    String input = "Sybit2019Gaienhofen";
    String output = "e07de83e6058810e2bf9e6fafe86237d7b903f889c667b84e0639989dfceebb6";

    Assert.assertEquals(output, userService.getSHA(input));

  }

  @Test(expected = IllegalArgumentException.class)
  public void addNewUser_InvalidEmail() {
    User user = userObject();
    user.setEmail("test");

    userService.addNewUser(user);
  }
}
