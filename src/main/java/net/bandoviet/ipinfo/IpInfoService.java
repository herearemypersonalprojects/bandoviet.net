package net.bandoviet.ipinfo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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
    try {
      HttpGet httpGet = new HttpGet(url);
      HttpResponse httpResponse = HTTP_CLIENT.execute(httpGet, new BasicHttpContext());
      String responseString;
      IpInfo ipResponse;
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
    return null;
  }

  public static IpInfo getIpInfo(String ip) {
    String url = "http://freegeoip.net/json/" + ip; // Using the API
    try {
      HttpGet httpGet = new HttpGet(url);
      HttpResponse httpResponse = HTTP_CLIENT.execute(httpGet, new BasicHttpContext());
      String responseString;
      IpInfo ipInfo;
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
    return null;
  }

  public static String getClientIP(HttpServletRequest request) {
    String ipAddress = request.getHeader("X-FORWARDED-FOR");

    if (ipAddress == null) {
      ipAddress = request.getRemoteAddr();
    }

    return ipAddress;
  }
}
