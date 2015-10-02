package net.bandoviet.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * user model.
 * 
 * @author quocanh
 *
 */

@Entity
public class User {

  @Id
  @NotNull
  @Size(max = 64)
  private String id; // id = user ip + invert(the first part of user email)

  @NotNull
  @Size(max = 64)
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "login")
  private String login;

  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  @Column(name = "last_login_date", nullable = false)
  private Date lastLoginDate;

  @Column(name = "last_active_time")
  private long lastActiveTime; // in milliseconds

  @Column(name = "first_connected_ip", nullable = false)
  private String firstConnectedIp;

  @Column(name = "last_connected_ip", nullable = false)
  private String lastConnectedIp;

  @Column(name = "telephone")
  private String telephone;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "job")
  private String job;

  @Column(name = "nationality")
  private String nationality; // mapping to country code

  @Column(name = "original_country")
  private String originalCountry; // mapping to country code

  @Column(name = "avatar_url")
  private String avatarUrl;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "access_level")
  private int accessLevel; // 0: normal; 1: admin; 2: superadmin

  @Column(name = "confident_level")
  private int confidentLevel;

  @Column(name = "thanks_given_by")
  private String thanksGivenBy; // 23;98;4 => thank by user'ids 23, 98, 4

  @Column(name = "favorite_address")
  private String favoriteAddress; // 87;75;8 => place'ids 87 and 75 and 8

  @Column(name = "is_newsletter")
  private String isNewsLetter; // whether he gets newletters

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "country")
  private String country; // mapping to country's code

  @Column(name = "latitude")
  private Double latitude;

  @Column(name = "longitude")
  private Double longitude;

  @Column(name = "gender")
  private String gender;

  @Column(name = "age_range")
  private int ageRange;

  @Column(name = "link")
  private String link;

  @Column(name = "language")
  private String language;

  User() {
  }

  public User(final String id, final String password) {
    this.id = id;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public long getLastActiveTime() {
    return lastActiveTime;
  }

  public void setLastActiveTime(long lastActiveTime) {
    this.lastActiveTime = lastActiveTime;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public String getFirstConnectedIp() {
    return firstConnectedIp;
  }

  public void setFirstConnectedIp(String firstConnectedIp) {
    this.firstConnectedIp = firstConnectedIp;
  }

  public String getLastConnectedIp() {
    return lastConnectedIp;
  }

  public void setLastConnectedIp(String lastConnectedIp) {
    this.lastConnectedIp = lastConnectedIp;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getOriginalCountry() {
    return originalCountry;
  }

  public void setOriginalCountry(String originalCountry) {
    this.originalCountry = originalCountry;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public int getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(int accessLevel) {
    this.accessLevel = accessLevel;
  }

  public int getConfidentLevel() {
    return confidentLevel;
  }

  public void setConfidentLevel(int confidentLevel) {
    this.confidentLevel = confidentLevel;
  }

  public String getThanksGivenBy() {
    return thanksGivenBy;
  }

  public void setThanksGivenBy(String thanksGivenBy) {
    this.thanksGivenBy = thanksGivenBy;
  }

  public String getFavoriteAddress() {
    return favoriteAddress;
  }

  public void setFavoriteAddress(String favoriteAddress) {
    this.favoriteAddress = favoriteAddress;
  }

  public String getIsNewsLetter() {
    return isNewsLetter;
  }

  public void setIsNewsLetter(String isNewsLetter) {
    this.isNewsLetter = isNewsLetter;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAgeRange() {
    return ageRange;
  }

  public void setAgeRange(int ageRange) {
    this.ageRange = ageRange;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }
}
