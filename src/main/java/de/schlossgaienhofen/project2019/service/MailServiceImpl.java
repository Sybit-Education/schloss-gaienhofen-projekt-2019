package de.schlossgaienhofen.project2019.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

  @Autowired
  private JavaMailSender emailSender;

  @Override
  public void sendSimpleMessage(String to, String subject, String text) {
    LOGGER.debug("--> sendSimpleMessage");
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("christian.wolter@sybit.de");
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
    LOGGER.debug("<-- sendSimpleMessage");
  }
}
