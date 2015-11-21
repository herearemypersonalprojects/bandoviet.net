package net.bandoviet.place;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Point of interest model.
 * The annotation Indexed marks Place as an entity which needs to be indexed by
 * Hibernate Search.
 * The annotation @Field and its parameters basically making the table columns "title", 
 * "description", and "author" indexable and during the indexing operation, the content 
 * of the column will be analyzed
 * @author quocanh
 *
 */
@Entity
@Table(name = "placesave")
public class PlaceSave {
  @Id
  @Column(name = "id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "placeid", nullable = true)
  private Long placeId;
  
  @Column(name = "score")
  // Moi khi 1 nguoi valid se tang 1 diem, du 4 diem thi duoc valided, 
  // hoac chi can admin hoac nguoi cung thanh pho valid
  private Integer score;
  
  @NotNull
  @Column(name = "title", nullable = false)
  private String title;
  
  @Column(name = "subtitle")
  private String subtitle;
  
  @Column(name = "title_without_accent")
  private String titleWithoutAccents;

  @Lob 
  @Column(name = "information", nullable = true)
  private String information;

  @Column(name = "email")
  @Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
  String email;

  @Column(name = "telephone")
  @Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
  String telephone;
  
  @Column(name = "address", nullable = false)
  @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
  private String address;
  
  @Column(name = "icon_path", nullable = true)
  private String iconPath;

  @Column(name = "image_path", nullable = true)
  private String imagePath;

  @NotNull
  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "city", nullable = true)
  private String city;

  @Column(name = "street_number")
  private String streetNumber;

  @Column(name = "route")
  private String route;

  @Column(name = "postal_code")
  private String postalCode;

  @NotNull
  @Column(name = "latitude")
  Double latitude;

  @NotNull
  @Column(name = "longitude")
  Double longitude;
  
  @NotNull
  @Column(name = "community_code", nullable = false)
  String communityCode;

  @NotNull
  @Column(name = "place_type", nullable = false)
  String placeType;

  @Column(name = "reference_url", nullable = true)
  String referenceUrl;

  @Column(name = "idlookid_url", nullable = true)
  String idLookitUrl;

  @Column(name = "created_by_user")
  String createdByUser;

  @Column(name = "created_by_user_fullname")
  String createdByUserFullname;
  
  @Column(name = "created_date")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") 
  Date createdDate;

  @Column(name = "updated_date")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") 
  Date updatedDate;

  @Column(name = "created_from_ip")
  String createdFromIp;

  @Column(name = "openTime")
  String openTime;

  @Column(name = "startTime")
  String startTime;
  
  @Column(name = "endTime")
  String endTime;  
  
  @Column(name = "size")
  Long size;  

  @Column(name = "organised_by")
  String organisedBy;

  /* PUBLIC METHODS */

  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public Long getPlaceId() {
    return placeId;
  }

  public void setPlaceId(Long placeId) {
    this.placeId = placeId;
  }

  @PrePersist
  protected void onCreate() {
    createdDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedDate = new Date();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  public String getIconPath() {
    return iconPath;
  }

  public void setIconPath(String iconPath) {
    this.iconPath = iconPath;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public String getCommunityCode() {
    return communityCode;
  }

  public void setCommunityCode(String communityCode) {
    this.communityCode = communityCode;
  }

  public String getPlaceType() {
    return placeType;
  }

  public void setPlaceType(String placeType) {
    this.placeType = placeType;
  }

  public String getReferenceUrl() {
    return referenceUrl;
  }

  public void setReferenceUrl(String referenceUrl) {
    this.referenceUrl = referenceUrl;
  }

  public String getIdLookitUrl() {
    return idLookitUrl;
  }

  public void setIdLookitUrl(String idLookitUrl) {
    this.idLookitUrl = idLookitUrl;
  }

  public String getCreatedByUser() {
    return createdByUser;
  }

  public void setCreatedByUser(String createdByUser) {
    this.createdByUser = createdByUser;
  }

  public String getCreatedByUserFullname() {
    return createdByUserFullname;
  }

  public void setCreatedByUserFullname(String createdByUserFullname) {
    this.createdByUserFullname = createdByUserFullname;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getCreatedFromIp() {
    return createdFromIp;
  }

  public void setCreatedFromIp(String createdFromIp) {
    this.createdFromIp = createdFromIp;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getOpenTime() {
    return openTime;
  }

  public void setOpenTime(String openTime) {
    this.openTime = openTime;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public String getOrganisedBy() {
    return organisedBy;
  }

  public void setOrganisedBy(String organisedBy) {
    this.organisedBy = organisedBy;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getTitleWithoutAccents() {
    return titleWithoutAccents;
  }

  public void setTitleWithoutAccents(String titleWithoutAccents) {
    this.titleWithoutAccents = titleWithoutAccents;
  }
}
