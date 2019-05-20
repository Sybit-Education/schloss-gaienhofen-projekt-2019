package de.schlossgaienhofen.project2019.service;

public interface MailService {

  /**
   * Send Mail
   *
   * @param to
   * @param subject
   * @param text
   */

  void sendSimpleMessage(String to, String subject, String text);
}
