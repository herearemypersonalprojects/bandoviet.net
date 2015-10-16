package net.bandoviet.ipinfo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

@Service
public class IpInfoService {
  private static final HttpClient HTTP_CLIENT = new DefaultHttpClient();
  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // Add a handler to handle unknown properties (in case the API adds new properties to the
    // response)
    MAPPER.addHandler(new DeserializationProblemHandler() {

      @Override
      public boolean handleUnknownProperty(DeserializationContext context, JsonParser jp,
          JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName)
              throws IOException {
        // Do not fail - just log
        String className = (beanOrClass instanceof Class) ? ((Class) beanOrClass).getName()
            : beanOrClass.getClass().getName();
        System.out
            .println("Unknown property while de-serializing: " + className + "." + propertyName);
        context.getParser().skipChildren();
        return true;
      }
    });
  }

  public static IpInfo getIpInfo(HttpServletRequest request) {
    String url = "http://freegeoip.net/json/" + getClientIP(request); // Using the API
    IpInfo ipResponse = new IpInfo();
    try {
      HttpGet httpGet = new HttpGet(url);
      HttpResponse httpResponse = HTTP_CLIENT.execute(httpGet, new BasicHttpContext());
      String responseString;
      
      if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new RuntimeException(
            "Sorry! Response Error. Status Code: " + httpResponse.getStatusLine().getStatusCode());
      }
      responseString = EntityUtils.toString(httpResponse.getEntity());
      ipResponse = MAPPER.readValue(responseString, IpInfo.class);
      return ipResponse;
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    return ipResponse;
  }
  
  /**
   * get ip informations from offline MaxMind database.
   * @param ip given request's ip
   * @return ipInfo
   */
  public static IpInfo getIpInfoMaxMind(String ip) {
    String filePath = System.getProperty("user.home") + "/maxmind/GeoLite2-City.mmdb";
    IpInfo ipInfo = new IpInfo();
    try {
      // A File object pointing to your GeoIP2 or GeoLite2 database
      File database = new File(filePath);

      // This creates the DatabaseReader object, which should be reused across
      // lookups.
      DatabaseReader reader = new DatabaseReader.Builder(database).build();

      InetAddress ipAddress = InetAddress.getByName(ip);

      // Replace "city" with the appropriate method for your database, e.g.,
      // "country".
      CityResponse response = reader.city(ipAddress);

      Country country = response.getCountry();
      ipInfo.setCountry_code(country.getIsoCode()); // 'US'
      ipInfo.setCountry_name(country.getName()); // 'United States'
      //System.out.println(country.getNames().get("zh-CN")); // '美国'

      Subdivision subdivision = response.getMostSpecificSubdivision();
      ipInfo.setRegion_name(subdivision.getName()); // 'Minnesota'
      ipInfo.setRegion_code(subdivision.getIsoCode()); // 'MN'

      City city = response.getCity();
      ipInfo.setCity(city.getName()); // 'Minneapolis'

      Postal postal = response.getPostal();
      ipInfo.setArea_code(postal.getCode()); // '55455'

      Location location = response.getLocation();
      ipInfo.setLatitude(location.getLatitude()); // 44.9733
      ipInfo.setLongitude(location.getLongitude()); // -93.2323     
    } catch (GeoIp2Exception ex) {
      System.out.println(ex.toString());
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    return ipInfo;
  }

  public static IpInfo getIpInfo(String ip) {
    String url = "http://freegeoip.net/json/" + ip; // Using the API
    IpInfo ipInfo = new IpInfo();
    try {
      HttpGet httpGet = new HttpGet(url);
      HttpResponse httpResponse = HTTP_CLIENT.execute(httpGet, new BasicHttpContext());
      String responseString;
      
      if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new RuntimeException(
            "Sorry! Response Error. Status Code: " + httpResponse.getStatusLine().getStatusCode());
      }
      responseString = EntityUtils.toString(httpResponse.getEntity());
      ipInfo = MAPPER.readValue(responseString, IpInfo.class);
      return ipInfo;
    } catch (Exception ex) {
      System.out.println(ex.toString());
    } finally {
      HTTP_CLIENT.getConnectionManager().shutdown();
    }
    return ipInfo;
  }

  public static String getClientIP(HttpServletRequest request) {
    String ipAddress = request.getHeader("X-FORWARDED-FOR");

    if (ipAddress == null) {
      ipAddress = request.getRemoteAddr();
    }

    return ipAddress;
  }
}
