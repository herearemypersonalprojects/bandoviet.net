package net.bandoviet.tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

/**
 * @author quocanh
 *
 */
public class NominatimReverseGeocodingJAPI {
  private final String NominatimInstance = "http://nominatim.openstreetmap.org"; 

  private int zoomLevel = 18;
    
  
  public NominatimReverseGeocodingJAPI(){}
  
  public NominatimReverseGeocodingJAPI(int zoomLevel){
    if(zoomLevel < 0 || zoomLevel > 18){
      System.err.println("invalid zoom level, using default value");
      zoomLevel = 18;
    }
    
    this.zoomLevel = zoomLevel;
  }
  
  public NominatimAddress getAdress(double lat, double lon){
    NominatimAddress result = null;    
    String urlString = NominatimInstance + "/reverse?format=json&NominatimAddressdetails=1&lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lon) + "&zoom=" + zoomLevel ;
    try {
      result =new NominatimAddress(getJSON(urlString), zoomLevel);
    } catch (IOException e) {
      System.err.println("Can't connect to server.");
      e.printStackTrace();
    }   
    return result;
  }
  
  
  private String getJSON(String urlString) throws IOException{
    URL url = new URL(urlString);
    URLConnection conn = url.openConnection();
    InputStream is = conn.getInputStream();
    String json = IOUtils.toString(is, "UTF-8"); 
    is.close();   
    return json;
  }
}