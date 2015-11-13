package net.bandoviet.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * In service layer, where the business logic should, 
 * we'd need something to retrieve the User by his id, 
 * email, list all the users and create a new one.
 * @author quocanh
 *
 */

@Service
public class UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
  
  @Autowired 
  UserRepository userRepository;
  
  public boolean canAccessUser(CurrentUser currentUser, Long userId) {
    return currentUser != null
            && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
  }
  
  public Optional<User> getUserById(long id) {
    return Optional.ofNullable(userRepository.findOne(id));
  }

  public Optional<User> getUserByEmail(String email) {
    LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
    return userRepository.findOneByEmail(email);
  }

  public Collection<User> getAllUsers() {
    return userRepository.findAll(new Sort("fullname"));
  }

  /**
   * Create a new user.
   */
  public User create(UserCreateForm form) {
    User user = new User();
    user.setFullname(form.getFullname());
    user.setAddress(form.getAddress());
    user.setCountry(form.getCountry());
    user.setCity(form.getCity());
    user.setLatitude(form.getLatitude());
    user.setLongitude(form.getLongitude());
    
    user.setEmail(form.getEmail());
    //user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword())); // never decoded
    user.setPassword(form.getPassword());
    user.setRole(form.getRole());
    user.setEnabled(false);
    return userRepository.save(user);
  }
}
