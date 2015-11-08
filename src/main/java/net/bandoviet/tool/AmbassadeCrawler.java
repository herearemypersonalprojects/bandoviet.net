package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;

@Service
public class AmbassadeCrawler {
  @Autowired PlaceService placeService;
  @Autowired  LinkedInCrawler tool;
  
  public void getAmbassades() {
    System.out.println("ok");
    String url = "http://www.mofa.gov.vn/en/bng_vietnam/dscqdd/ns050119142406/newsitem_print_preview";
    try {
      File input = new File("/Users/quocanh/Documents/ws/data/coquanvn/newsitem_print_preview.html");
      Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
      //Document doc = Jsoup.connect(url).get();
      Elements table = doc.select("#table1");
    
      
      //System.out.println(table.html());
      Elements items = table.select("tr");
      
      for (int i = 1; i< items.size(); i ++) {
        //System.out.println(items.get(i).text());
        Place place = getPlace(items.get(i));
        if (place != null) {
          placeService.save(place, null);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }    
  }

  private Place getPlace(Element item) { 
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setPlaceType(PlaceType.ADMINISTRATION.toString());
    
    Elements info = item.select("td");
    //title
    System.out.println(info.get(1).text());
    place.setTitle(info.get(1).text());
    
    //address
    System.out.println(info.get(2).text());
    place.setAddress(info.get(2).text());
    if (!tool.getAddress(place, place.getAddress())) {
      return null;
    }
   
    // telephone:
    System.out.println(info.get(3).text());
    place.setTelephone(info.get(3).text());
    
    //email
    System.out.println(info.get(5).text());
    place.setEmail(info.get(5).text());
    
    return place;
  }
}
