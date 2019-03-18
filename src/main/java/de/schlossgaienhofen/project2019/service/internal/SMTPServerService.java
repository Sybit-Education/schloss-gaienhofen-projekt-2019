package de.schlossgaienhofen.project2019.service.internal;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Internal Service to simulate SMTP-Server for sending E-Mails
 * 
 * @author ssr
 */
@Service
public class SMTPServerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SMTPServerService.class);

  @Value("${smtpserver.enabled}")
  String enabled = "";

  @Value("${smtpserver.hostName}")
  String hostName = "";

  @Value("${smtpserver.port}")
  String port = "";

  private SMTPServer smtpServer;

  public SMTPServerService() {
  }

  @PostConstruct
  public void start() {
    if (enabled.equalsIgnoreCase("true")) {
      SimpleMessageListenerImpl l = new SimpleMessageListenerImpl();
      smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(l));
      smtpServer.setHostName(this.hostName);
      smtpServer.setPort(Integer.valueOf(port));
      smtpServer.start();
      LOGGER.info("SMTP Server is running for domain " + smtpServer.getHostName() + " on port " + smtpServer.getPort());
    } else {
      LOGGER.warn("SMTP Server NOT ENABLED by settings ");
    }
  }

  @PreDestroy
  public void stop() {
    if (enabled.equalsIgnoreCase("true")) {
      LOGGER.info("Stopping SMTP Server for domain " + smtpServer.getHostName() + " on port " + smtpServer.getPort());
      smtpServer.stop();
    }
  }

  public boolean isRunning() {
    return smtpServer.isRunning();
  }
}
