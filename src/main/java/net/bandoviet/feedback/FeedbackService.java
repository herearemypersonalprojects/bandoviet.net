package net.bandoviet.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

import net.bandoviet.ipinfo.IpInfo;
import net.bandoviet.ipinfo.IpInfoService;
import net.bandoviet.mail.Mail;
import net.bandoviet.mail.MailService;

/**
 * Services for feedback's messages.
 * @author quocanh
 *
 */
@Service
public class FeedbackService {
  @Autowired
  private FeedbackRepository feedbackRepository;
  
  @Autowired
  private MailService mailService;
  /**
   * Setup and save feedback's info.
   * @param feedback from the user.
   */
  public void save(Feedback feedback) {
    IpInfo ipInfo = IpInfoService.getIpInfo(feedback.getSendFromIp());
    feedback.setCity(ipInfo.getCity());
    feedback.setCountry(ipInfo.getCountry_code());
    feedback.setLatitude(ipInfo.getLatitude());
    feedback.setLongitude(ipInfo.getLongitude());
    feedback.setCreatedDate(new Date(System.currentTimeMillis()));
    
    feedbackRepository.save(feedback);
    
    // gui vao email cua ca user va cua ca admin
    Mail mail = new Mail();
    mail.setTo(feedback.getEmail());
    mail.setCc("bandoviet.net@gmail.com");
    mail.setFrom("bandoviet.net@gmail.com");
    mail.setSubject("Thanks for your feedback at bandoviet.net: " + feedback.getSubject());
    mail.setText("Xin chào " + feedback.getName() 
        + "\n\n Cảm ơn bạn đã viết cho chúng tôi với nội dung: \n\n" 
        + feedback.getMessage() 
        + "\n\n Chúng tôi sẽ cố gắng có phản hồi cho bạn sớm nhất."
        + "\n\nThay mặt ban điều hành,"
        + "\n Bản đồ Việt");
    
    mailService.sendMail(mail);
  }
  
  /**
   * Create a new feedback from given parameters.
   */
  public void save(String name, String email, String subject, String message, String ip) {
    Feedback feedback = new Feedback();
    feedback.setName(name);
    feedback.setEmail(email);
    feedback.setSubject(subject);
    feedback.setMessage(message);
    feedback.setSendFromIp(ip);
    
    save(feedback);
  }
}
