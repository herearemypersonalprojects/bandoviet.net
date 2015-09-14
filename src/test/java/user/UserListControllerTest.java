package user;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for user list controller.
 * @author quocanh
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserListControllerTest {
  
  @Mock
  private UserService userService;
  
  private UserListController userController;
  
  @Before
  public void setUp() throws Exception {
   userController = new UserListController(userService);
  }
}
