/**
 * all services to work with user accounts. 
 */

package user;

import java.util.List;

/**
 * user service class.
 * @author quocanh
 *
 */
public interface UserService {
  
  User save(User user);
  
  List<User> getList();
}
