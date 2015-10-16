package net.bandoviet.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Get feedback from users (Feedback, Suggestion, Question, Other).
 *
 */
@Controller
public class FeedbackController {
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
                         @RequestParam String message) {
    System.out.println(name + ":" + email + ":" + subject + ":" + message);
    
    return "Thank you!";
  }
}
