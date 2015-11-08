package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;

import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;

@Service
public class SocieteCrawler {
  @Autowired
  PlaceService placeService;
  @Autowired
  LinkedInCrawler tool;

  public void getData() {
    System.out.println("Starting ..");
    for (int i = 4; i < VietnameseWords.surnames.length; i++) {
      String page = "http://dirigeant.societe.com/cgi-bin/listedir?champs="
          + VietnameseWords.surnames[i] + "&nrpp=5000";

      Document doc;
      try {
        doc = Jsoup.connect(page).get();
        Elements linkresult = doc.select(".linkresult");
        for (Element link : linkresult) {
          int start = link.html().indexOf("<br>");
          int end = link.html().indexOf("</p>", start);
          String activity = "";
          if (0 < start && start < end) {
            activity = link.html().substring(start, end).trim();
          }

          String title = link.text().replace(" >", "").replaceAll("-", " ");
          System.out.println(title);
          String tmp = link.select(".resultat").get(0).select("span").text().trim();
          String surname = tmp.substring(tmp.lastIndexOf(" ")).trim();
          
          if (!activity.isEmpty()
              && ((i< 8 && surname.equalsIgnoreCase(VietnameseWords.surnames[i])) 
                  || (i >= 8 && VietnameseWords.isTwoVietnamese(title)))) {
            Place place = new Place();
            place.setCommunityCode("VN");
            place.setPlaceType(PlaceType.INDIVIDUAL.toString());


            place.setTitle(title);
        

            String url = "http://dirigeant.societe.com" + link.attr("href");
            System.out.println(url);
            place.setReferenceUrl(url);
            getInfo(place, url);

          }

        }
      } catch (IOException e) {
        System.out.println("Co loi: " + e.getMessage());
      }
      //break; // bo di sau nhe TODO
    }
  }

  private void getInfo(Place place, String url) {
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements info = doc.select("#synthese");
      System.out.println(info.text());
      place.setInformation(info.text());

      Elements addressUrl = doc.select(".adressemandat a");
      System.out.println(addressUrl.attr("href"));
      try {
        if (!StringUtils.isEmpty(addressUrl.attr("href").trim())) {
          getAddress(place, addressUrl.attr("href"));
        }

      } catch (Exception e) {
        System.out.println("Co loi: " + e.getMessage());
      }

    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    }

  }

  private void getAddress(Place place, String url) {
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
      place.setAddress(address.text().substring(i));
      try {
        Thread.sleep(1000); // 1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      if (tool.getAddress(place, place.getAddress())) {
        place.setImagePath(
            "https://t2.ftcdn.net/jpg/00/77/61/57/160_F_77615732_82cRBjnYITlFFRDayLZ3aUVMkkHh4Ty2.jpg");
        placeService.save(place, null);

        Place placeCompany = new Place();
        placeCompany.setCommunityCode("VN");
        placeCompany.setPlaceType(PlaceType.COMPANY.toString());
        placeCompany.setAddress(place.getAddress());
        placeCompany.setLatitude(place.getLatitude());
        placeCompany.setLongitude(place.getLongitude());
        placeCompany.setCity(place.getCity());
        placeCompany.setCountry("FR");

        placeCompany.setReferenceUrl(url);
        placeCompany
            .setImagePath("http://www.creativehope.co.jp/global/images/icon/icon_3_company.png");

        // Company name
        System.out.println(doc.select("#identite_deno").text());
        String name = "";
        if (doc.select("#dir0 a") != null && !doc.select("#dir0 a").isEmpty()) {
          name = doc.select("#dir0 a").get(0).text().trim();
          if (name.contains("En savoir plus")) {
            name = "";
          }
        }
        placeCompany
            .setTitle(doc.select("#identite_deno").text() + (name.isEmpty() ? "" : " - " + name));

        // Company info
        System.out.println(doc.select("#synthese").text());
        placeCompany.setInformation(doc.select("#synthese").text());

        placeService.save(placeCompany, null);
      }

    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    }
  }

  public boolean getAddressFromGoogle(Place place, String location) {

    if (StringUtils.isEmpty(location)) {
      return false;
    }

    GeoApiContext context = new GeoApiContext()
        .setApiKey("AIzaSyCJbKbcTqdaVh5oJVTOBTHPaBDViLurLxM");
    GeocodingResult[] results;
    try {
      results = GeocodingApi.geocode(context, location)
          .await();
      System.out.println(results[0].formattedAddress);

      if (results != null && results.length > 0) {
        double latitude = results[0].geometry.location.lat;

        double longitude = results[0].geometry.location.lng;

        String address = results[0].formattedAddress;

        String country = "";
        for (int j = 0; j < results[0].addressComponents.length; j++) {
          AddressComponent e = results[0].addressComponents[j];

          for (int k = 0; k < e.types.length; k++) {
            if (e.types[k].equals("country")) {
              country = e.shortName;
              break;
            }
          }
        }

        System.out.println(latitude + "," + longitude + ": " + address + " -- " + country);
        place.setAddress(address);
        place.setLatitude(Double.valueOf(latitude));
        place.setLongitude(Double.valueOf(longitude));
        place.setCountry(country);

      }

    } catch (Exception e1) {
      System.out.println(e1.getMessage());
      return false;
    }
    return true;
  }
}
