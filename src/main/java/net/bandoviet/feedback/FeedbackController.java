package net.bandoviet.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import net.bandoviet.ipinfo.IpInfoService;

/**
 * Get feedback from users (Feedback, Suggestion, Question, Other).
 *
 */
@Controller
public class FeedbackController {
  @Autowired
  private FeedbackService feedbackService;
  
  /**
   * Get feedback from visitors.
   * @param name visitor's name
   * @param email visitor's email
   * @param message visitor's message.
   * @return thanks
   */
  @RequestMapping("/feedback")
  @ResponseBody
  public String feedback(@RequestParam String name, 
                         @RequestParam String email,
                         @RequestParam String subject,
                         @RequestParam String message,
                         HttpServletRequest request) {
    System.out.println(name + ":" + email + ":" + subject + ":" + message);
    // save to database
    feedbackService.save(name, email, subject, message, IpInfoService.getClientIP(request));
    return "Thank you!";
  }
}
