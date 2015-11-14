package net.bandoviet.user;

import net.bandoviet.tool.EmailUtils;
import net.bandoviet.tool.VietnameseWords;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



/**
 * To check if the password and repeated passwords are matching. To check if the email exists in a
 * database.
 * 
 * @author quocanh
 *
 */

@Component
public class UserCreateFormValidator implements Validator {

  @Autowired
  UserService userService;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(UserCreateForm.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserCreateForm form = (UserCreateForm) target;
    validateVietnameseName(errors, form);
    validatePasswords(errors, form);
    validateEmail(errors, form);
    validateAddress(errors, form);
  }
  
  private void validateAddress(Errors errors, UserCreateForm form) {
    if (StringUtils.isBlank(form.country)) {
      errors.rejectValue("address", "address.invalidated", "Địa chỉ thiếu hoặc không đúng");
    }
  }
  
  private void validateVietnameseName(Errors errors, UserCreateForm form) {
    if (StringUtils.isNotBlank(form.getFullname()) 
        && !VietnameseWords.isVietnamese(form.getFullname())) {
      errors.rejectValue("fullname", "fullname.invalidated", "Họ tên thiếu hoặc không đúng");
    }
  }

  private void validatePasswords(Errors errors, UserCreateForm form) {
    if (StringUtils.isNotBlank(form.getPasswordRepeated()) 
        && !form.getPassword().equals(form.getPasswordRepeated())) {
      errors.rejectValue("passwordRepeated", 
          "password.no_match", "Hai lần nhập mật khẩu không giống nhau");
    }
  }

  private void validateEmail(Errors errors, UserCreateForm form) {
 
    if (StringUtils.isNotBlank(form.getEmail()) && !EmailUtils.isValide(form.getEmail())) {
      errors.rejectValue("email", "email.invalided", "Email không đúng");
    } else {
      if (userService.getUserByEmail(form.getEmail()).isPresent()) {
        errors.rejectValue("email", "email.exists", "Email này đã tồn tại trong hệ thống");
      }        
    }
  

  }

}
