package net.bandoviet.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * The UserDetails is just an interface. It describes a user having username, password, list of
 * GrantedAuthority objects and some self-explanatory flags for various reasons of account
 * invalidation. So we need to return an implementation. There is at least one provided by Spring
 * Security, it's org.springframework.security.core.userdetails.User. . It can be used, but the
 * tricky part is to relate our User domain object to UserDetails, as it may be needed for
 * authorization. It can be done in multiple ways: 1. Make User domain object to implement
 * UserDetails directly - it will allow to return the User exactly as received from UserService. The
 * downside is that is going to 'pollute' the domain object with a code related to Spring Security.
 * 2. Use the provided implementation org.springframework.security.core.userdetails.User, just map a
 * User entity to it. This is fine, however it would be nice to have some additional information
 * about the user available, like id, direct access to role, or whatever else. 3. Therefore, the
 * third solution is to extend the provided implementation and add whatever info can be needed, or
 * just a whole User object as it is. The last option is what I used here, so CurrentUser:
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

  private static final long serialVersionUID = 1L;

  private User user;

  /**
   * constructor.
   */
  public CurrentUser(User user) {
    super(user.getEmail(), user.getPassword(), true, true, true, true,
        AuthorityUtils.createAuthorityList(user.getRole().toString()));
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public Long getId() {
    return user.getId();
  }

  public Role getRole() {
    return user.getRole();
  }

}
