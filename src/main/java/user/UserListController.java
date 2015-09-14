/**
 * Receive requests to control users.
 */

package user;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

/**
 * User controller class.
 * 
 * @author quocanh
 *
 */
@Controller
public class UserListController {
  private final UserService userService;

  @Inject
  public UserListController(final UserService userService) {
    this.userService = userService;
  }

  /**
   * Prepare information for the home page.
   * @return the home page.
   */
  @RequestMapping("/user_list.html")
  public ModelAndView getListUsersView() {
    ModelMap model = new ModelMap();
    model.addAttribute("users", userService.getList());
    return new ModelAndView("user_list", model);
  }
}
