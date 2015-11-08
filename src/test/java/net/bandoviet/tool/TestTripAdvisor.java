package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import net.bandoviet.place.PlaceType;

public class TestTripAdvisor {

  
  public void test2() {
    URL url;

    try {
      // get URL content
      url = new URL("http://www.tripadvisor.com/Search?q=vi%E1%BB%87t#&o=0");
      URLConnection conn = url.openConnection();

      // open the stream and put it into BufferedReader
      BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

      String inputLine;
      /*
      while ((inputLine = br.readLine()) != null) {
        System.out.println(inputLine);
      }
      */
      
      //save to this filename
      String fileName = "/Users/quocanh/test.html";
      File file = new File(fileName);

      if (!file.exists()) {
        file.createNewFile();
      }

      //use FileWriter to write file
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      while ((inputLine = br.readLine()) != null) {
        bw.write(inputLine);
      }

      bw.close();
      br.close();
  
      System.out.println("Done");

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void getPlaces(Elements elements, String type) {
    for (Element element : elements) {
      getPlace(element, type);
    }
  }
  
  public void getPlace(Element element, String type) {
    String name = element.select(".poi-info").select(".title").select("span").get(0).text();
    System.out.println(type+ " : " + name);
    
    String address = element.select(".poi-info").select(".address").get(0).text();
    System.out.println(address);
    
    String photo = element.select(".thumbnail img").attr("src");
    System.out.println(photo);   
  }
  @Test
  public void test() {
    List<String> inProgress = new ArrayList<String>();
    inProgress.add("http://www.tripadvisor.com/Search?q=viet&ssrc=e&o=90");
    inProgress.add("http://www.tripadvisor.com/Search?q=viet&ssrc=e&o=150");
    for (String url : inProgress) {
      try {
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc.data());
        Elements restaus = doc.select(".EATERY");
        //System.out.println(restaus.html());
        getPlaces(restaus, PlaceType.RESTAURANT.toString());

        
        // Attraction 
        Elements attractions = doc.select(".ATTRACTIONS");
        getPlaces(attractions, PlaceType.TOURISM.toString());
        
        // result LODGING
        Elements lodgings = doc.select(".LODGING");
        getPlaces(lodgings, PlaceType.TOURISM.toString());
        
        // VACATION_RENTALS
        Elements rentals = doc.select(".VACATION_RENTALS");
        getPlaces(rentals, PlaceType.TOURISM.toString());
        
        // ****
        Elements pages = doc.select(".pageNumber");
        for (Element page : pages) {
          String str = page.attr("onclick");
          int start = str.indexOf("'") + 1;
          int end = str.lastIndexOf("'");
          if (start > -1 && end > start) {
            System.out.println(str.substring(start, end));
          }
          
        }
        
      } catch (Exception e) {
        System.out.println("Co loi: " + e.getMessage());
      }      
    }

  }
}
