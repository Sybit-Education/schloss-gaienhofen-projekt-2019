/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schlossgaienhofen.project2019.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ssr
 */
@Service
public class MailService {

  public JavaMailSender emailSender;
    
  public MailService(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }
  

  /**
   * Send Mail
   * @param to
   * @param subject
   * @param text 
   */
  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage(); 
    message.setTo(to); 
    message.setSubject(subject); 
    message.setText(text);
    emailSender.send(message);
  }  
}
