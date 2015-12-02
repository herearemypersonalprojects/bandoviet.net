package net.bandoviet.crawl;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import net.bandoviet.place.Place;

public class trangvang {

  @Test
  public void test() {
    String URL = "http://www.trangvang.biz/ten/m/?trang=1";
    // 
    // 

   
    //ABCDEFGHIJKLMNOPQRSTUVWXYZ

    try {
      Document doc = Jsoup.connect(URL).timeout(10000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      // Document doc = Jsoup.connect(url).get();
      Elements items = doc.select(".storeList ol li");      
      System.out.println(items.size());
      
      Elements pages = doc.select(".pagination ul li");      
      System.out.println(pages.size());
      for (int idx = 1; idx < pages.size(); idx++) {
        System.out.println(URL.substring(0, URL.lastIndexOf("=") + 1) + idx);
      }
      
      
      /*
      for (Element item : items) {
        System.out.println(item.select("a").attr("title") + " - http://www.trangvang.biz" + item.select("a").attr("href"));
        
        Place place = getPlace("http://www.trangvang.biz" + item.select("a").attr("href"));
        
        try {
          Thread.sleep(500 * MathTool.randInt(1, 10)); // 1000 milliseconds is one second.
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
      */
      
    } catch (Exception e) {
      System.out.println("Co loi: " + e.getMessage());
    }
    
  }
  
  public Place getPlace(String url) {
    //String url = "http://www.trangvang.biz/tp-hcm/quan-go-vap/ca-kieng-son-thuy-664316.html";
    Place place = new Place();
    try {
      Document doc = Jsoup.connect(url).timeout(10000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      
      Elements title = doc.select("#businessAddress h2");
      System.out.println(title.text());
      
      Elements subtitle = doc.select("#businessAddress a");
      if (subtitle.size() > 1)
      System.out.println(subtitle.get(2).text());
      
      Elements type = doc.select("#businessAddress a");
      if (type.size() > 0)
      System.out.println(type.get(1).text());
      
      Elements address = doc.select("#businessAddress address");    
      Elements telephone = address.select(".telephone");
      telephone.select(".hide").remove();
      if (StringUtils.isNotBlank(telephone.text())) {
        System.out.println(telephone.text().substring(telephone.text().indexOf(":") + 1));
        telephone.remove();      
      }

      System.out.println(address.text());
      
      return place;
    } catch (Exception e) {
      System.out.println("Co loi: " + e.getMessage());
    }   
    return null;
  }

}
