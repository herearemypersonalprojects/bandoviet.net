package net.bandoviet.type;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean.
 * @author quocanh
 *
 */
@Entity
@Table(name = "type")
public class Type {
  @Id
  @Column(name = "id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "created_date")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") 
  Date createdDate;
  
  @Column(name = "created_by_user", nullable = false)
  private String createdByUser;
  
  @Column(name = "code", nullable = false)
  private String code;
  
  @Column(name = "name", nullable = false)
  private String name;
  
  @Column(name = "nhom", nullable = false) // PERSONAL, nhom, PUBLIC
  private String nhom;
  
  @Column(name = "security_level", nullable = false) // 1: ONLY ME; 2: CAN SHARE; 3: PUBLIC
  private Integer securityLevel;

  /* PUBLIC METHODS */

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreatedByUser() {
    return createdByUser;
  }

  public void setCreatedByUser(String createdByUser) {
    this.createdByUser = createdByUser;
  }

  public String getNhom() {
    return nhom;
  }

  public void setNhom(String nhom) {
    this.nhom = nhom;
  }

  public Integer getSecurityLevel() {
    return securityLevel;
  }

  public void setSecurityLevel(Integer securityLevel) {
    this.securityLevel = securityLevel;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  
}
