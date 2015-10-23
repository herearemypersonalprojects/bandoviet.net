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
    
  public static void main(String[] args){
     /*
    if(args.length < 1){
      System.out.println("use -help for instructions");
    }
    else if(args.length < 2){
      if(args[0].equals("-help")){
        System.out.println("Mandatory parameters:");
        System.out.println("   -lat [latitude]");
        System.out.println("   -lon [longitude]");
        System.out.println ("\nOptional parameters:");
        System.out.println("   -zoom [0-18] | from 0 (country) to 18 (street NominatimAddress), default 18");
        System.out.println("   -osmid       | show also osm id and osm type of the NominatimAddress");
        System.out.println("\nThis page:");
        System.out.println("   -help");
      }
      else
        System.err.println("invalid parameters, use -help for instructions");
    }
    else{
      boolean latSet = false;
      boolean lonSet = false;
      boolean osm = false;
      
      double lat = -200;
      double lon = -200;
      int zoom = 18;
      
      for (int i = 0; i < args.length; i++) {
        if(args[i].equals("-lat")){         
          try{  
              lat = Double.parseDouble(args[i+1]);  
          }  
          catch(NumberFormatException nfe){  
              System.out.println("Invalid latitude");
              return;
          }  
          
          latSet = true;
          i++;
          continue;
        }   
        else if(args[i].equals("-lon")){
          try{  
              lon = Double.parseDouble(args[i+1]);  
          }  
          catch(NumberFormatException nfe){  
              System.out.println("Invalid longitude");
              return;
          } 
          
          lonSet = true;
          i++;
          continue;
        }
        else if(args[i].equals("-zoom")){
          try{  
              zoom = Integer.parseInt(args[i+1]);  
          }  
          catch(NumberFormatException nfe){  
              System.out.println("Invalid zoom");
              return;
          } 
          
          i++;
          continue;
        }
        else if(args[i].equals("-osm")){
          osm = true;
        }
        else{
          System.err.println("invalid parameters, use -help for instructions");
          return;
        }
      }
      
      if(latSet && lonSet){
        NominatimReverseGeocodingJAPI nominatim = new NominatimReverseGeocodingJAPI(zoom);
        NominatimAddress NominatimAddress = nominatim.getAdress(lat, lon);
        System.out.println(NominatimAddress);
        if(osm){
          System.out.print("OSM type: " + NominatimAddress.getOsmType()+", OSM id: " + NominatimAddress.getOsmId());
        }
      }
      else{
        System.err.println("please specifiy -lat and -lon, use -help for instructions");
      }     
    }       
    */
  }
  
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