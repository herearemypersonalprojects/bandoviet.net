package net.bandoviet.mail;



import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configurable
public class MailConfig {
  @Bean
  public JavaMailSenderImpl javaMailSenderImpl(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    //Set gmail email id
    mailSender.setUsername("bandoviet.net@gmail.com");
    //Set gmail email password
    mailSender.setPassword("bandoviet2015");
    Properties prop = mailSender.getJavaMailProperties();
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.debug", "false");
    return mailSender;
  }
}