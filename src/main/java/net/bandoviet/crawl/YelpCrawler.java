package net.bandoviet.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;
import net.bandoviet.tool.StringTools;

@Service
public class YelpCrawler {
  
  @Autowired PlaceService placeService;
  
  List<String> linksInProgress = new ArrayList<String>();
  List<String> linksAlready = new ArrayList<String>();
  Hashtable<Integer, Double> latitudes = new Hashtable<Integer, Double>();
  Hashtable<Integer, Double> longitudes = new Hashtable<Integer, Double>();
  
  public void run(String country, String city) {
    String url = "http://www.yelp.com/search?find_desc=vietnamese&find_loc=" + city + "&start=0";
    
    linksInProgress.add(url);
    while (!linksInProgress.isEmpty()) {
      String link = linksInProgress.remove(0);
      linksAlready.add(link);
      System.out.println("DANG XU LY: " + link);
      getPage(link, country, city);
    }
  }
  
  private void getPage(String url, String country, String city) {
    
    try {
      Document doc = Jsoup.connect(url).timeout(10000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      //Document doc = Jsoup.connect(url).get();
      Elements items = doc.select(".regular-search-result");
      System.out.println(items.size());
      
      getCoordinations(doc);
      
      for (int i = 0; i< items.size(); i ++) {
        Place place = getPlace(items.get(i));
        if (place != null) {
          place.setCity(city);
          place.setCountry(country);
          place.setLatitude(latitudes.get(Integer.valueOf(place.getIdLookitUrl())));
          place.setLongitude(longitudes.get(Integer.valueOf(place.getIdLookitUrl())));
          
          placeService.save(place, null);
        }
      }
      
      // lay duong linkk trang ke tiep
      Elements links = doc.select(".search-pagination").select("li a");
      for (Element link : links) {
        System.out.println("http://www.yelp.com" + link.attr("href"));
        if (!linksInProgress.contains(link) && !linksAlready.contains(link)) {
          linksInProgress.add("http://www.yelp.com" + link.attr("href"));
        }
      }
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }    
  }
  
  private void getCoordinations(Document doc) {
   
    // Lay toa do (lat, lng) cac POIs
    String key1 = "yelp.www.init.search.Controller(";
    String key2 = "#find_desc";
    String str = doc.html();
    
    int start = str.indexOf(key1) + key1.length();
    int end = str.indexOf(key2, start);
    
    String json = str.substring(start, end);
    
    //System.out.println(json);
    
    
    int idx = 0;
    while (json.indexOf("key", idx + 3) > 0) {
      int position = json.indexOf("key", idx + 3);
      String numStr = json.substring(position + 5, json.indexOf(",", position + 5)).trim();
      if (!StringTools.isNumeric(numStr)) {
        return;
      }
      //System.out.println(String.valueOf(numStr));
      Integer key = Integer.valueOf(numStr);
      
      String substr = json.substring(idx, position);
      //System.out.println(substr);
      start = substr.lastIndexOf("latitude") + 11;
      //System.out.println(start);
      end = substr.indexOf(",", start);
      
      //System.out.println(substr.substring(start, end));
      Double latitude = Double.valueOf(substr.substring(start, end));
      
      
      start = substr.lastIndexOf("longitude") + 12;
      end = substr.indexOf("}", start);
      //System.out.println(substr.substring(start, end));
      Double longitude = Double.valueOf(substr.substring(start, end));
      
      latitudes.put(key, latitude);
      longitudes.put(key, longitude);
      
      idx = json.indexOf("key", idx + 3);
    }    
  }
  
  private Place getPlace(Element item) {

    Place place = new Place();
    place.setCommunityCode("VN");
    //place.setReferenceUrl(url);
    place.setPlaceType(PlaceType.RESTAURANT.toString());
    
    String key = item.select(".natural-search-result").attr("data-key");
    place.setIdLookitUrl(key);
    //System.out.println(key);
    
    String photo = "http:" + item.select(".photo-box-img").attr("src");
    place.setImagePath(photo);
    //System.out.println(photo);
    
    String title = item.select(".photo-box-img").attr("alt");
    place.setTitle(title);
    //System.out.println(title);
    
    String telephone = item.select(".biz-phone").get(0).text();
    place.setTelephone(telephone);
    //System.out.println(telephone);
    
    String address = item.select("address").text();
    place.setAddress(address);
    //System.out.println(address);
    
    if (photo == null || title == null || address == null) {
      return null;
    } else {
      return place;
    }
   
  }
}
