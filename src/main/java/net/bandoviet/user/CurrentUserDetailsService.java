package net.bandoviet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The UserDetailsService is an interface the Spring Security uses to find out if the user using the
 * login form exists, what their password should be, and what authority it has in the system. It has
 * a single method: UserDetails loadUserByUsername( username) throws UsernameNotFoundException; It
 * is expected that the method loadUserByUsername() returns UserDetails instance if the username
 * exists, or throw UsernameNotFoundException if it doesn't.
 * 
 * @author quocanh
 *
 */

@Service
public class CurrentUserDetailsService implements UserDetailsService {

  @Autowired
  UserService userService;

  @Override
  public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userService.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
        String.format("User with email=%s was not found", email)));
    return new CurrentUser(user);
  }
}
