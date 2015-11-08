package net.bandoviet.tool;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class TestSociete {
  private static final String URL = "http://dirigeant.societe.com/cgi-bin/listedir?champs=Huynh&nrpp=15";
  
  @Test
  public void test() {
    for (int i = 0; i < VietnameseWords.surnames.length; i++) {
      String link = "http://dirigeant.societe.com/cgi-bin/listedir?champs=" + VietnameseWords.surnames[i] + "&nrpp=5000";
    }
    
    Document doc;
    try {
      doc = Jsoup.connect(URL).get();
      Elements linkresult = doc.select(".linkresult");
      for (Element link : linkresult) {
        
        int start = link.html().indexOf("<br>");
        int end = link.html().indexOf("</p>", start);
        String activity = link.html().substring(start, end).trim();
        if (!activity.isEmpty()) {
          String title = link.text().replace(" >", "");
          System.out.println(title);
          
          String url = "http://dirigeant.societe.com" + link.attr("href");
          System.out.println(url);
          getInfo(url);
        }
        
      }
     

      Elements name = doc.select(".full-name");
    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    } 
  }
  
  private void getInfo(String url) {
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements info = doc.select("#synthese");
      System.out.println(info.text());
      
      Elements addressUrl = doc.select(".adressemandat a");
      System.out.println(addressUrl.attr("href"));
      getAddress(addressUrl.attr("href"));
      
    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    } 
    
  }
  
  private void getAddress(String url) {
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements address = doc.select("#identitetext");
      address.select(".textred").remove();
    
      int i = 0;
      for (i = 0; i < address.text().length(); i++) {
        if (Character.isDigit(address.text().charAt(i))) {
          break;
        }
      }
      System.out.println(address.text().substring(i));
      
      // Company name
      System.out.println(doc.select("#identite_deno").text());
      
      // Company info
      System.out.println(doc.select("#synthese").text());
      
    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    } 
  }
}
