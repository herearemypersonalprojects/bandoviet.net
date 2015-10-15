package net.bandoviet.log;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Log all activities (requests, actions,...)
 * @author quocanh
 *
 */
@Entity
@Table(name = "log")
public class Log {
  @Id
  @Column(name = "id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "created_date")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") 
  Date createdDate;
  
  @Column(name = "send_from_ip")
  private String sendFromIp;
  
  @Column(name = "city")
  private String city;
  
  @Column(name = "country")
  private String country;
  
  @Column(name = "user_id")
  private String userId;
  
  @Column(name = "request")
  private String request;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSendFromIp() {
    return sendFromIp;
  }

  public void setSendFromIp(String sendFromIp) {
    this.sendFromIp = sendFromIp;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
