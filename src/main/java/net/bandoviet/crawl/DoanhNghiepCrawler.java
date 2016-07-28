package net.bandoviet.crawl;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class DoanhNghiepCrawler {
  

  
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
    for (int i = 1; i < 15096; i++) {
      String url = "http://hochiminh.vietnamnay.com/doanh-nghiep/trang-" + String.valueOf(i) + ".html?sort=4";
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
      Elements items = doc.getElementsByClass("enterprise-list-item");
      System.out.println(items.size());
      /*
      Elements pages = doc.select(".pagination ul li");
      for (int idx = 1; idx < pages.size(); idx++) {
        //System.out.println(link.substring(0, link.lastIndexOf("=") + 1) + idx);
        String url = link.substring(0, link.lastIndexOf("=") + 1) + idx;
        if (!linksInProgress.contains(url) && !linksAlready.contains(url)) {
          linksInProgress.add(link.substring(0, link.lastIndexOf("=") + 1) + idx);
        }
      }
          */
      
      for (Element item : items) {
        //System.out.println(item.select("a").attr("title") + " - http://www.trangvang.biz" + item.select("a").attr("href"));
        
        Place place = getPlace(item);
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
  
  public Place getPlace(Element item) {

    Place place = new Place();
    place.setCommunityCode("VN");
    place.setCountry("VN");
    place.setLatitude(0.0);
    place.setLongitude(0.0);
    place.setPlaceType("COMPANY");

    place.setImagePath("http://hochiminh.vietnamnay.com/" + item.select("img").attr("src"));

    place.setTitle(item.getElementsByClass("enterprise-name").text());

    Elements nodes = item.getElementsByClass("node");
    for(Element node : nodes) {
      if (node.text().equals("Địa chỉ")) {
        place.setAddress(node.nextElementSibling().text());
      } else
      if (node.text().equals("Điện thoại")) {
        place.setTelephone(node.nextElementSibling().text());
      } else
      if (node.text().equals("Email")) {
        place.setEmail(node.nextElementSibling().text());
      } else
      if (node.text().equals("Website")) {
        place.setIdLookitUrl(node.nextElementSibling().text());
      } else
      if (node.text().equals("Lĩnh vực KD")) {
        place.setSubtitle(node.nextElementSibling().text());
      }
    }



    if (StringUtils.isNotEmpty(place.getImagePath()) && place.getLatitude() > 0 && place.getLongitude() > 0 ) {
      return place;
    } else {
      return null;
    }
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
