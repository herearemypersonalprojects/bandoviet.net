package net.bandoviet.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author quocanh
 *
 */
@Controller
public class AdminController {
  @RequestMapping(value = "/admin/valid/{id}", method = RequestMethod.GET)
  public String validPlace(Map<String, Object> model, @PathVariable Long id) {
    return "valid";
  }
}
