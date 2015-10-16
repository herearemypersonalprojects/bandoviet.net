package net.bandoviet.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

import net.bandoviet.ipinfo.IpInfo;
import net.bandoviet.ipinfo.IpInfoService;

/**
 * Services for feedback's messages.
 * @author quocanh
 *
 */
@Service
public class FeedbackService {
  @Autowired
  private FeedbackRepository feedbackRepository;
  
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
