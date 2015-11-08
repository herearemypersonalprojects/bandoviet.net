package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceType;

public class TestYELP {
  private static final String URL = "http://www.yelp.com/search?find_desc=vietnamese&find_loc=paris&start=0";
  
  @Test
  public void test() {
    try {
      Document doc = Jsoup.connect(URL)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      //Document doc = Jsoup.connect(url).get();
      Elements items = doc.select(".regular-search-result");
      System.out.println(items.size());
      
      for (int i = 1; i< items.size(); i ++) {
        Place place = getPlace(items.get(i));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }    
  }
  
  private Place getPlace(Element item) {
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setPlaceType(PlaceType.RESTAURANT.toString());
    
   
    String photo = "http:" + item.select(".photo-box-img").attr("src");
    System.out.println(photo);
    
    String title = item.select(".photo-box-img").attr("alt");
    System.out.println(title);
    
    String telephone = item.select(".biz-phone").get(0).text();
    System.out.println(telephone);
    
    String address = item.select("address").text();
    System.out.println(address);
    
    return place;
  }
}
