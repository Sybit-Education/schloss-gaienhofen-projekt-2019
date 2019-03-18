package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Test
  public void addNewUser() {
    userService.addNewUser("firstNameTest", "nameTest", "test@mail.com");
    User user = userService.findUserByEmail("test@mail.com");
    Assert.assertEquals("nameTest", user.getName());
  }
}
