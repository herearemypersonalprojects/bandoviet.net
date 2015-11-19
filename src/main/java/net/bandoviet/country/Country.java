package net.bandoviet.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
  @Id
  @Column(name = "code")
  private String code;
  
  @Column(name = "name")
  private String name;

  @Column(name = "native")
  private String nativeName;
  
  @Column(name = "phone")
  private String phone;
  
  @Column(name = "continent")
  private String continent;
  
  @Column(name = "capital")
  private String capital;
  
  @Column(name = "currency")
  private String currency;
  
  @Column(name = "languages")
  private String languages;

  /* PUBLIC METHODS */
  
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNativeName() {
    return nativeName;
  }

  public void setNativeName(String nativeName) {
    this.nativeName = nativeName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public String getCapital() {
    return capital;
  }

  public void setCapital(String capital) {
    this.capital = capital;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getLanguages() {
    return languages;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }
  

  
  
  
}
