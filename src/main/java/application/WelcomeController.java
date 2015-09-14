/**
 * Homepage controller.
 */

package application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Receive requests from homepage.
 * @author quocanh
 *
 */
@Controller
public class WelcomeController {

  @Value("${application.message:Hello World}")
  private String message = "Hello World";

  /**
   * The first request when user open the homepage.
   * @param model communication between view and controller.
   * @return the tempate view of homepage.
   */
  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    model.put("time", new Date());
    model.put("message", this.message + "ok");
    return "index";
  }

  @RequestMapping("/foo")
  public String foo(Map<String, Object> model) {
    throw new RuntimeException("Foo");
  }

}
