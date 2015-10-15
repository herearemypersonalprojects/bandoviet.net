package net.bandoviet.ipinfo;

public class IpInfo {
  private String ip;
  private String country_code;
  private String country_name;
  private String region_code;
  private String region_name;
  private String city;
  private String zipcode;
  private Double latitude;
  private Double longitude;
  private String metro_code;
  private String area_code;

  /**
   * @return the ip
   */
  public String getIp() {
      return ip;
  }

  /**
   * @param ip the ip to set
   */
  public void setIp(String ip) {
      this.ip = ip;
  }

  /**
   * @return the country_code
   */
  public String getCountry_code() {
      return country_code;
  }

  /**
   * @param country_code the country_code to set
   */
  public void setCountry_code(String country_code) {
      this.country_code = country_code;
  }

  /**
   * @return the country_name
   */
  public String getCountry_name() {
      return country_name;
  }

  /**
   * @param country_name the country_name to set
   */
  public void setCountry_name(String country_name) {
      this.country_name = country_name;
  }

  /**
   * @return the region_code
   */
  public String getRegion_code() {
      return region_code;
  }

  /**
   * @param region_code the region_code to set
   */
  public void setRegion_code(String region_code) {
      this.region_code = region_code;
  }

  /**
   * @return the region_name
   */
  public String getRegion_name() {
      return region_name;
  }

  /**
   * @param region_name the region_name to set
   */
  public void setRegion_name(String region_name) {
      this.region_name = region_name;
  }

  /**
   * @return the city
   */
  public String getCity() {
      return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
      this.city = city;
  }

  /**
   * @return the zipcode
   */
  public String getZipcode() {
      return zipcode;
  }

  /**
   * @param zipcode the zipcode to set
   */
  public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
  }

  /**
   * @return the latitude
   */
  public Double getLatitude() {
      return latitude;
  }

  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(Double latitude) {
      this.latitude = latitude;
  }

  /**
   * @return the longitude
   */
  public Double getLongitude() {
      return longitude;
  }

  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(Double longitude) {
      this.longitude = longitude;
  }

  /**
   * @return the metro_code
   */
  public String getMetro_code() {
      return metro_code;
  }

  /**
   * @param metro_code the metro_code to set
   */
  public void setMetro_code(String metro_code) {
      this.metro_code = metro_code;
  }

  /**
   * @return the area_code
   */
  public String getArea_code() {
      return area_code;
  }

  /**
   * @param area_code the area_code to set
   */
  public void setArea_code(String area_code) {
      this.area_code = area_code;
  }

  @Override
  public String toString() {
      return "IPInfo{"
              + "\"ip\":\"" + ip + "\""
              + ",\"country_code\":\"" + country_code + "\""
              + ",\"country_name\":\"" + country_name + "\""
              + ",\"region_code\":\"" + region_code + "\""
              + ",\"region_name\":\"" + region_name + "\""
              + ",\"city\":\"" + city + "\""
              + ",\"zipcode\":\"" + zipcode + "\""
              + ",\"latitude\":" + latitude
              + ",\"longitude\":" + longitude
              + ",\"metro_code\":\"" + metro_code + "\""
              + ",\"area_code\":\"" + area_code + "\""
              + '}';
  }
}
