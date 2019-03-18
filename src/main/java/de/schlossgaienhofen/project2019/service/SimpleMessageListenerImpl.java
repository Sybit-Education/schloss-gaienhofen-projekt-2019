/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schlossgaienhofen.project2019.service;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.subethamail.smtp.helper.SimpleMessageListener;

/**
 *
 * @author ssr
 */
public class SimpleMessageListenerImpl implements SimpleMessageListener {

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
      // ... here we go with email message ...
    } catch (MessagingException ex) {
      //
    }
  }
}
