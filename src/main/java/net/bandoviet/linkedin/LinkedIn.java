package net.bandoviet.linkedin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "linkedin")
public class LinkedIn {
  @Id
  @Column(name = "id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "url")
  String url;
  
  @Column(name = "name")
  String name;
  
  @Column(name = "country")
  String country;
  
  @Column(name = "foundtimes")
  Float foundTimes; // So lan xuat hien trong duong dan dang: https://vn.linkedin.com/pub/dir/Ngoc%20anh/Nguyen => Join LinkedIn to see all 235 profiles. => 235 / 25 
  
  /* PUBLIC METHODS */
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Float getFoundTimes() {
    return foundTimes;
  }

  public void setFoundTimes(Float foundTimes) {
    this.foundTimes = foundTimes;
  }

  
  
}
