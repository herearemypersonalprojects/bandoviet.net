/**
 * Homepage controller.
 */

package net.bandoviet.home;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Receive requests from homepage.
 * @author quocanh
 *
 */
@Controller
public class HomeController {

  @Value("${application.message:Hello World}")
  private String message;
  
  /**
   * The first request when user open the homepage.
   * @param model communication between view and controller.
   * @return the tempate view of homepage.
   */
  @RequestMapping("/welcome")
  public String welcome(Map<String, Object> model ) {
    model.put("message", this.message + "ok");
    return "welcome";
  }
  
  /**
   * The first request when user open the homepage.
   * @param model communication between view and controller.
   * @return the tempate view of homepage.
   */
  @RequestMapping("/about")
  public String about(Map<String, Object> model ) {
    model.put("message", this.message + "ok");
    return "about";
  }
  
  @RequestMapping("/unsupported")
  public String unsupported(Map<String, Object> model) {
    return "unsupported";
  }
  
  @RequestMapping("/foo")
  public String foo(Map<String, Object> model) {
    throw new RuntimeException("Foo");
  }

}
