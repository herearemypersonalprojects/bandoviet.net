package net.bandoviet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import net.bandoviet.place.PlaceService;

/**
 * Sign in, sign up.
 * @author quocanh
 *
 */
@Controller
public class UserController {
  
  @Autowired PlaceService placeService;
  
  /**
   * Homepage
   * @param model communication between view and controller.
   * @return the login page if user has not yet connected or index page otherwise.
   */
  @RequestMapping("/login")
  public String login(Map<String, Object> model ) {
    model.put("places", placeService.getRandom(20));
    return "login";
  }
}
