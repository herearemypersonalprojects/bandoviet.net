package net.bandoviet.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Mail services.
 * 
 * @author quocanh
 *
 */
@Service
public class MailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

  /**
   * Sending mail.
   */
  public void sendMail(Mail mail) {
    try {
      @SuppressWarnings("resource")
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.register(MailConfig.class);
      ctx.refresh();
      JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
      mailMsg.setFrom(mail.getFrom());
      mailMsg.setTo(mail.getTo());
      mailMsg.setSubject(mail.getSubject());
      mailMsg.setText(mail.getText());
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      System.out.println(e);
    }
  }

  /**
   * gui mail.
   */
  public void sendMailwithAttachment(Mail mail, FileSystemResource file, String fileName) {
    try {
      @SuppressWarnings("resource")
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.register(MailConfig.class);
      ctx.refresh();
      JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      // Pass true flag for multipart message
      MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true);
      mailMsg.setFrom(mail.getFrom());
      mailMsg.setTo(mail.getTo());
      mailMsg.setSubject(mail.getSubject());
      mailMsg.setText(mail.getText());
      
      // FileSystemResource object for Attachment
      mailMsg.addAttachment(fileName, file);
      
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("Co loi khi gui mail toi dia chi: " + mail.getTo());
    }
  }
}
