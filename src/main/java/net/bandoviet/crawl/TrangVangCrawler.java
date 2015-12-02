package net.bandoviet.crawl;

import net.bandoviet.country.CountryService;
import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.tool.MathTool;
import net.bandoviet.tool.StringTools;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Service
public class TrangVangCrawler {
  

  
  @Autowired
  PlaceService placeService;
  @Autowired
  CountryService countryService;

  List<String> linksInProgress = new ArrayList<String>();
  List<String> linksAlready = new ArrayList<String>();
  

  /**
   * Start.
   */
  public void run() {
    for (int i = 0; i < StringTools.ABC.length(); i++) {
      String url = "http://www.trangvang.biz/ten/" + StringTools.ABC.charAt(i) + "/?trang=1";
      linksInProgress.add(url);
    }
    
    while (!linksInProgress.isEmpty()) {
      String link = linksInProgress.remove(0);
      if (linksAlready.indexOf(link) < 0) {
        linksAlready.add(link);
        runLink(link);
      }
    }
  }
  
  private void runLink(String link) {
    
    try {
      Document doc = Jsoup.connect(link).timeout(10000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      // Document doc = Jsoup.connect(url).get();
      Elements items = doc.select(".storeList ol li");      
      //System.out.println(items.size());
      
      Elements pages = doc.select(".pagination ul li");    
      //System.out.println(pages.size());
      for (int idx = 1; idx < pages.size(); idx++) {
        //System.out.println(link.substring(0, link.lastIndexOf("=") + 1) + idx);
        String url = link.substring(0, link.lastIndexOf("=") + 1) + idx;
        if (!linksInProgress.contains(url) && !linksAlready.contains(url)) {
          linksInProgress.add(link.substring(0, link.lastIndexOf("=") + 1) + idx);
        }
      }    
      
      for (Element item : items) {
        //System.out.println(item.select("a").attr("title") + " - http://www.trangvang.biz" + item.select("a").attr("href"));
        
        Place place = getPlace("http://www.trangvang.biz" + item.select("a").attr("href"));
        if (place != null) {
          placeService.save(place, null);
        }
        
        try {
          Thread.sleep(200 * MathTool.randInt(1, 10)); // 1000 milliseconds is one second.
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
      
      
    } catch (Exception e) {
      System.out.println("Co loi: " + e.getMessage());
    }
    
  }
  
  public Place getPlace(String url) {
    //String url = "http://www.trangvang.biz/tp-hcm/quan-go-vap/ca-kieng-son-thuy-664316.html";
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setCountry("VN");
    place.setLatitude(0.0);
    place.setLongitude(0.0);
    place.setPlaceType("Vietnam");
    
    try {
      Document doc = Jsoup.connect(url).timeout(10000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      
      Elements title = doc.select("#businessAddress h2");
      //System.out.println(title.text());
      place.setTitle(title.text());
      
      Elements subtitle = doc.select("#businessAddress a");
      if (subtitle.size() > 1) {
        //System.out.println(subtitle.get(2).text());
        place.setSubtitle(subtitle.get(2).text());
      }
      
      Elements type = doc.select("#businessAddress a");
      if (type.size() > 0) {
        //System.out.println(type.get(1).text());
        place.setPlaceType(type.get(1).text());
      } 
      
      
      Elements address = doc.select("#businessAddress address");    
      Elements telephone = address.select(".telephone");
      telephone.select(".hide").remove();
      if (StringUtils.isNotBlank(telephone.text())) {
        //System.out.println(telephone.text().substring(telephone.text().indexOf(":") + 1));
        place.setTelephone(telephone.text().substring(telephone.text().indexOf(":") + 1));
        telephone.remove();      
      }

      //System.out.println(address.text());
      place.setAddress(address.text());
      
      return place;
    } catch (Exception e) {
      System.out.println("Co loi: " + e.getMessage());
    }   
    return null;
  }
}
