package net.bandoviet.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import net.bandoviet.mail.Mail;
import net.bandoviet.mail.MailService;
import net.bandoviet.tool.AccentRemover;
import net.bandoviet.user.CurrentUser;


/**
 * Services for type's messages.
 * @author quocanh
 *
 */
@Service
public class TypeService {
  @Autowired
  private TypeRepository typeRepository;
  
  @Autowired
  private MailService mailService;
  /**
   * Setup and save type's info.
   * @param type from the user.
   */
  public Type save(Type type) {
    Type result = null;
    type.setCreatedDate(new Date(System.currentTimeMillis()));
   
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return null;
    }
    
    type.setCreatedByUser(authentication.getName());
    
    try {
      result = typeRepository.save(type);
    } catch (Exception e) {
      System.out.println("Bi loi: " + e.getMessage());
      return null;
    }
    
    CurrentUser user =  (CurrentUser) authentication.getPrincipal();
    
    // gui vao email cua ca user va cua ca admin
    Mail mail = new Mail();
    mail.setTo(type.getCreatedByUser());
    mail.setBbc("quocanh263@gmail.com");
    mail.setFrom("bandoviet.net@gmail.com");
    mail.setSubject("Thanks for creating a new group at bandoviet.net: " + type.getName());
    mail.setText("Xin chào  " + user.getFullname() + ","
        + "\n\nCảm ơn bạn đã tạo nhóm bản đồ mới cho chúng tôi với nội dung: \n\n" 
        + type.getName()
        + "\n\nChúng tôi sẽ cập nhật thông tin của bạn."
        + "\n\nThay mặt ban điều hành,"
        + "\n Bản đồ Việt");
    
    mailService.sendMail(mail);
    return result;
  }
  
  /**
   * Create a new type from given parameters.
   */
  public Type save(String name, String nhom, Integer securityLevel) {
    Type type = new Type();
    type.setName(name);
    type.setNhom(nhom);
    type.setCode(AccentRemover.toUrlFriendly(name)); //TODO: Kiem tra ten da bi trung truoc khi save
    type.setSecurityLevel(securityLevel);
    
    return save(type);
  }
  
  public List<Type> findAll() {
    return typeRepository.findAll();
  }
}
