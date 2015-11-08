package net.bandoviet.tool;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class TripAdvisorCrawler {
  @Autowired PlaceService placeService;
  @Autowired LinkedInCrawler tool;
  
  public void getPlaces(Elements elements, String type, String url) {
    for (Element element : elements) {
      getPlace(element, type, url);
    }
  }
  
  public void getPlace(Element element, String type, String url) {
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setReferenceUrl("http://www.tripadvisor.com/");
    place.setPlaceType(type);
    
    String name = element.select(".poi-info").select(".title").select("span").get(0).text();
    System.out.println(type+ " : " + name);
    place.setTitle(name);
    
    if (VietnameseWords.isVietnamese(name)) {
      String address = element.select(".poi-info").select(".address").get(0).text();
      System.out.println(address);
      place.setAddress(address);
      
      String photo = element.select(".thumbnail img").attr("src");
      System.out.println(photo);   
      place.setImagePath(photo);
      
      try {
        if (tool.getAddress(place, place.getAddress())) {
          place.setAddress(address);
          placeService.save(place, null);
          System.out.println("Da them: " + place.getTitle());
        } {
          String add = place.getAddress().trim().substring(place.getAddress().lastIndexOf(" "));
          if (tool.getAddress(place, add)) {
            place.setAddress(address);
            placeService.save(place, null);
            System.out.println("Da them: " + place.getTitle());
          }
        }
       } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());
          }
    }
    
  }
  
  public void getFromTripAdvisor() {
    // build seeds
    for (int i = 0; i < VietnameseWords.RESTAU.split(" ").length; i++) {
    for (int j = 0; j < VietnameseWords.RESTAU.split(" ").length; j++) {
      String term = VietnameseWords.RESTAU.split(" ")[i] + "+" + VietnameseWords.RESTAU.split(" ")[j];
      //String url = "http://www.tripadvisor.com/Search?q=" + term + "&ssrc=e&o=" ;
      //http://www.tripadvisor.com/Search?q=banh+cuon&geo=&pid=3826&typeaheadRedirect=true&redirect=&startTime=1446294451449&uiOrigin=MASTHEAD&returnTo=__2F__Restaurant__5F__Review__2D__g186605__2D__d3795065__2D__Reviews__2D__Pho__5F__Viet__2D__Dublin__5F__County__5F__Dublin__2E__html
      String url = "http://www.tripadvisor.com/Search?q="+term+"&geo=&pid=3826&typeaheadRedirect=true&redirect=&startTime=1446294451449&uiOrigin=MASTHEAD&returnTo=__2F__Restaurant__5F__Review__2D__g186605__2D__d3795065__2D__Reviews__2D__Pho__5F__Viet__2D__Dublin__5F__County__5F__Dublin__2E__html#&o=";
      processData(url);
    }}
  }
  private void processData(String racine) {
    List<String> linksInProgress = new ArrayList<String>();
    List<String> linksAlready = new ArrayList<String>();
    
    linksInProgress.add(racine + "0");
    while (!linksInProgress.isEmpty() && linksInProgress.size() > 0) {
      try {
        Thread.sleep(1000); // 1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      List<String> newLst = new ArrayList<String>(linksInProgress);
      for (String url : newLst) {
        try {
          linksAlready.add(url);
          linksInProgress.remove(url);
          System.out.println("Remain links: " + linksInProgress.size());   
          if (linksInProgress.size() <= 0) break;
          try {
            Thread.sleep(1000); // 1000 milliseconds is one second.
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          Document doc = Jsoup.connect(url).get();
          
          //System.out.println(doc.data());
          Elements restaus = doc.select(".EATERY");
          //System.out.println(restaus.html());
          getPlaces(restaus, PlaceType.RESTAURANT.toString(), url);
          
          // Attraction 
          Elements attractions = doc.select(".ATTRACTIONS");
          getPlaces(attractions, PlaceType.TOURISM.toString(), url);
          
          // result LODGING
          Elements lodgings = doc.select(".LODGING");
          getPlaces(lodgings, PlaceType.SERVICE.toString(), url);
          
          // VACATION_RENTALS
          Elements rentals = doc.select(".VACATION_RENTALS");
          getPlaces(rentals, PlaceType.SERVICE.toString(), url);
          
          // ****
          Elements pages = doc.select(".pageNumber");
          for (Element page : pages) {
            String str = page.attr("onclick");
            int start = str.indexOf("'") + 1;
            int end = str.lastIndexOf("'");
            if (start > -1 && end > start) {
              String number = str.substring(start, end);
              if (!linksAlready.contains(racine + number) 
                  && !linksInProgress.contains(racine + number)) {
                linksInProgress.add(racine + number);
              }
            }
            
          }
          
          
        } catch (Exception e) {
          System.out.println("Co loi: " + e.getMessage());
        } // END TRY CATCH      
      } // END FOR
    } // END WHILE  
  } // END PROCEDURE processData
}
