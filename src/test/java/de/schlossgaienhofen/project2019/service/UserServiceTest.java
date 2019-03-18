package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;
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

  @Test
  public void addNewUser() {
    userService.addNewUser("firstNameTest", "nameTest", "test@mail.com", "testPW");
    User user = userService.findUserByEmail("test@mail.com");
    Assert.assertEquals("nameTest", user.getName());
  }

  @Test
  public void getSha() throws NoSuchAlgorithmException {

    String input = "Sybit2019Gaienhofen";
    String output = "e07de83e6058810e2bf9e6fafe86237d7b903f889c667b84e0639989dfceebb6";

    Assert.assertEquals(output, userService.getSHA(input));

  }
}
