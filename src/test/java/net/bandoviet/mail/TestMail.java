package net.bandoviet.mail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

import net.bandoviet.user.User;

@RunWith(MockitoJUnitRunner.class)
public class TestMail {
  private JavaMailSender javaMailService;
  private User user;

  @Before
  public void setUp() throws Exception {
    user = new User();
    user.setEmail("quocanh263@gmail.com");
    user.setFullname("Lê Quốc Anh");
  }

  public void test() {
    try {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.register(MailConfig.class);
      ctx.refresh();
      JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      // Pass true flag for multipart message
      MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true);

      String activeLink = "";
      StringBuffer content = new StringBuffer();
      content.append("Xin chào " + user.getFullname() + ",\n");
      content.append("\nCảm ơn bạn đã đăng ký tham gia bản đồ người Việt trên thế giới.");
      content.append("\nĐể kích hoạt tài  khoản, mời bạn bấm vào đường dẫn sau: " + activeLink);
      content.append("\n\nThay mặt ban điều hành,");
      content.append("\nBản Đồ Việt");

      mailMsg.setFrom("bandoviet.net@gmail.com");
      mailMsg.setTo(user.getEmail());
      mailMsg.setSubject("Kich hoat bandoviet.net");
      mailMsg.setText(content.toString());

      // FileSystemResource object for Attachment
      // FileSystemResource file = new FileSystemResource(new File("D:/cp/pic.jpg"));
      // mailMsg.addAttachment("myPic.jpg", file);

      mailSender.send(mimeMessage);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void test2() {
    try {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.register(MailConfig.class);
      ctx.refresh();
      JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
      mailMsg.setFrom("arvindraivns02@gmail.com");
      mailMsg.setTo("quocanh263@gmail.com");
      mailMsg.setSubject("Test mail");
      mailMsg.setText("Hello World!" + "http://bandoviet.net/active/878946872");
      mailSender.send(mimeMessage);
      System.out.println("---Done---");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}