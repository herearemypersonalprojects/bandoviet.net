package net.bandoviet.tool;


import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class HncityCrawler {
  @Autowired
  PlaceService placeService;
  
  public void getDataFrom360hncity() {
    List<String> seeds = new ArrayList<String>();
    seeds.add("http://360.hncity.org/?-%C4%90en-mieu-");
    seeds.add("http://360.hncity.org/?-Vuon-Thien-");
    
    for (String url : seeds) {
      try {
        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select(".menu ul li");
        for (Element link : links) {
          String furl = link.select("a").attr("href");
          if (furl.indexOf("./") == 0) {
            String src = "http://360.hncity.org" + furl.substring(1);
            System.out.println(src);
            Place place = getPlace(src);
            if (place != null) {
              placeService.save(place, null);
            }
          }
          
        }
      } catch (Exception e) {
        System.out.println("Co loi tu: " + e.getMessage());
      }
    }
  }

  private Place getPlace(String url) { // id, title, address, country,latitude, longitude,
    // place_type
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setReferenceUrl(url);
    place.setPlaceType(PlaceType.TOURISM.toString());

    Document doc;
    try {
      doc = Jsoup.connect(url).timeout(500000).get();

      Elements name = doc.select(".cartouche h1");
      place.setTitle(name.text());
      System.out.println(name.text());
      
      Elements date = doc.select(".published");
      String datestr = date.text();
      System.out.println(datestr);
      
      Elements summary = doc.select(".chapo p");
      String summarystr = summary.text(); 
      System.out.println(summarystr);
      
      Elements map = doc.select(".texte iframe");
      String src = map.get(map.size() - 1).attr("src");
      System.out.println(src);
      int start = src.indexOf("ll=") + 3;
      int end = src.indexOf("&", start);
      String latlng = src.substring(start, end);
      System.out.println(latlng);
      
      System.out.println(latlng.split(",")[0] + " : " + latlng.split(",")[1]);
      if (!getAddressFromGoogleMap(place, latlng.split(",")[0], latlng.split(",")[1])) {
        return null;
      }
      
      Elements photo = doc.select(".texte img");
      if (photo != null && !photo.isEmpty()) {
        System.out.println(photo.get(0).attr("src"));
        place.setImagePath("http://360.hncity.org/" + photo.get(0).attr("src"));          
      }
     
      map.remove();
      
      Elements info = doc.select(".texte");
      String infostr = info.html();
      //System.out.println( );
      
      place.setInformation(summarystr + "<br>" + infostr + "<br>" + datestr);
      
   
    } catch (IOException e) {
      return null;
    }
    return place;
  }
  
  public boolean getAddressFromGoogleMap(Place place, String lat, String lng) {
    try {
      final Geocoder geocoder = new Geocoder();

      GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(new LatLng(lat, lng)) 
          .setLanguage("en").getGeocoderRequest();
      
      

      GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
      List<GeocoderResult> results = geocoderResponse.getResults();
      
      if (results != null && !results.isEmpty()) {
        float latitude = results.get(0).getGeometry().getLocation().getLat().floatValue();

        float longitude = results.get(0).getGeometry().getLocation().getLng().floatValue();

        String address = results.get(0).getFormattedAddress();

        String country = "";
        for (GeocoderAddressComponent e :results.get(0).getAddressComponents()) {
          if (e.getTypes().contains("country")) {
            country = e.getShortName();
            break;
          }         
        }
        
        System.out.println(latitude + "," + longitude + ": " + address + " -- " + country );
        place.setAddress(address);
        place.setLatitude(Double.valueOf(latitude));
        place.setLongitude(Double.valueOf(longitude));
        place.setCountry(country);
             
      }

    } catch (IOException e) {
      System.out.println("Co loi tu google geocoder: " + e.getMessage());
      return false;
    }
    return true;
  }
}
