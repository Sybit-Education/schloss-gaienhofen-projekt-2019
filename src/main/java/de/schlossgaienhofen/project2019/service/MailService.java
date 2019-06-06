package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.User;

import javax.validation.constraints.NotNull;

public interface MailService {

  /**
   * Send Mail
   *
   * @param to
   * @param subject
   * @param text
   */

  void sendSimpleMessage(String to, String subject, String text);

  /**
   * @param id
   * @param user
   */

  void sendEmailByEventIdAndUser(@NotNull Long id, @NotNull User user);
}
