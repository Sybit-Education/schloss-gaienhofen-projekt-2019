package de.schlossgaienhofen.project2019.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

  @Autowired
  MailService mailService;

  @Test
  public void testSendMail() {

    mailService.sendSimpleMessage("hello@sybit.de", "Test Betreff", "Test Body");

  }
}
