package de.schlossgaienhofen.project2019.service.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.helper.SimpleMessageListener;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class SimpleMessageListenerImpl implements SimpleMessageListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMessageListenerImpl.class);

  public SimpleMessageListenerImpl() {
  }

  @Override
  public boolean accept(String from, String recipient) {
    return true;
  }

  @Override
  public void deliver(String from, String recipient, InputStream data) {
    Session session = Session.getDefaultInstance(new Properties());
    try {
      MimeMessage m = new MimeMessage(session, data);
      LOGGER.info("Mail deliver: " + m.getSubject());
      // ... here we go with email message ...

    } catch (MessagingException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
  }
}
