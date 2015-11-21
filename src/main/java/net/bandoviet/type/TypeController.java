package net.bandoviet.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Get feedback from users (Feedback, Suggestion, Question, Other).
 *
 */
@Controller
public class TypeController {
  @Autowired
  private TypeService typeService;
  
  /**
   * create a new map group.
   */
  @RequestMapping("/createtype")
  public String createtype(@RequestParam String name, 
                         @RequestParam Integer securityLevel) {
     // save to database name=sdfsdfsq&securityLevel=3
    Type type = typeService.save(name, "GROUP", securityLevel);
    
    return "redirect:/create?placeType=" + type.getCode();
  }
}
