package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceType;

public class TestAmbassades {
  @Test
  public void testS() {
    System.out.println("Software Engineer Harvey Nash avril 2012 – août 2013 (1 an 5 mois) Telecom project".length());

  }
  public void test() {
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
    //address
    System.out.println(info.get(2).text());
   
    // telephone:
    System.out.println(info.get(3).text());
    
    //email
    System.out.println(info.get(5).text());
    return place;
  }

}
