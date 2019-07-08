package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.EventUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class MailServiceImpl implements MailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  private EventService eventService;

  @Value("${spring.mail.username}")
  private String email;

  @Override
  public void sendSimpleMessage(String to, String subject, String text) {
    LOGGER.debug("--> sendSimpleMessage");
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(email);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
    LOGGER.debug("--> Message send to:" + to);
    LOGGER.debug("<-- sendSimpleMessage");
  }

  public void sendEmailByEventIdAndUser(@NotNull Long id, @NotNull EventUser user) {
    Event event = eventService.getEventById(id);

    StringBuilder stringBuilder = new StringBuilder();
    String eventName = event.getTitle();

    String subject = "Anmeldung zur AG " + eventName;
    stringBuilder
      .append("Hallo ")
      .append(user.getFirstName())
      .append(",\n Hier die Bestätigung, dass Sie sich zur AG ")
      .append(eventName).append(" angemeldet haben");

    sendSimpleMessage(user.getEmail(), subject, stringBuilder.toString());
  }
}
