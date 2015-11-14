package net.bandoviet.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

import net.bandoviet.mail.Mail;
import net.bandoviet.mail.MailService;

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
  
  @Autowired
  MailService mailService;
  
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

  public Optional<User> enable(User user) {
    user.setEnabled(true);
    return Optional.ofNullable(userRepository.save(user));
  }
  /**
   * Create a new user.
   */
  public User create(UserCreateForm form, String ip) {
    User user = new User();
    user.setFullname(form.getFullname());
    user.setAddress(form.getAddress());
    user.setCountry(form.getCountry());
    user.setCity(form.getCity());
    user.setLatitude(form.getLatitude());
    user.setLongitude(form.getLongitude());
    user.setConfidentLevel(form.getConfidentLevel());
    
    user.setEmail(form.getEmail());
    //user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword())); // never decoded
    user.setPassword(form.getPassword());
    user.setRole(form.getRole());
    user.setEnabled(false);
    
    user.setCreatedDate(Calendar.getInstance().getTime());
    user.setLastLoginDate(Calendar.getInstance().getTime());
    user.setFirstConnectedIp(ip);
    user.setLastConnectedIp(ip);
    
    return userRepository.save(user);
  }
  
  /**
   * Gui duong link kich hoat cho nguoi dung.
   */
  public void sendActivationMail(User user) {
    String activeLink = "http://bandoviet.net/active/" + user.getId();
    StringBuffer content = new StringBuffer();
    content.append("Xin chào " + user.getFullname() + ",\n");
    content.append("\nCảm ơn bạn đã đăng ký tham gia bản đồ người Việt trên thế giới.\n");
    content.append("\nEmail: " + user.getEmail());
    content.append("\nMật khẩu: " + user.getPassword());
    content.append("\n\nĐể kích hoạt tài  khoản, mời bạn bấm vào đường dẫn sau: " + activeLink);
    content.append("\n\nThay mặt ban điều hành,");
    content.append("\nBản Đồ Việt");
    
    Mail mail = new Mail();
    mail.setTo(user.getEmail());
    mail.setFrom("bandoviet.net@gmail.com");
    mail.setSubject("Kich hoat bandoviet.net");
    mail.setText(content.toString());
    
    mailService.sendMail(mail);
  }
}
