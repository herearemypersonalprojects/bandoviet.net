package net.bandoviet.user;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Lop dung de tao tai khoan truoc khi luu vao CSDL.
 * This will function as a data transfer object (DTO) 
 * between the web layer and service layer. It's annotated 
 * by Hibernate Validator validation constraints and sets some sane defaults. 
 * Notice that it's slightly different than User object, 
 * therefore as much as I'd wish to "leak" the User entity into the web layer I really cannot.
 * @author quocanh
 *
 */

public class UserCreateForm {
  
  @NotEmpty(message = "Chưa nhập họ tên")
  private String fullname = "";
  
  @NotEmpty(message = "Chưa nhập địa chỉ")
  private String address = "";
  
  String city = "";
  
  String country = "";
  
  Double latitude = 0.0d;
  
  Double longitude = 0.0d;
  
  private int confidentLevel = 50;
  
  @NotEmpty(message = "Chưa nhập Email")
  private String email = "";

  @NotEmpty(message = "Chưa nhập mật khẩu")
  private String password = "";

  @NotEmpty(message = "Chưa xác nhận mật khẩu")
  private String passwordRepeated = "";

  @NotNull
  private Role role = Role.USER;


  /* PUBLIC METHODS */
  
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public int getConfidentLevel() {
    return confidentLevel;
  }

  public void setConfidentLevel(int confidentLevel) {
    this.confidentLevel = confidentLevel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordRepeated() {
    return passwordRepeated;
  }

  public void setPasswordRepeated(String passwordRepeated) {
    this.passwordRepeated = passwordRepeated;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  
}
