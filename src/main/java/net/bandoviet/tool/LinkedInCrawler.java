package net.bandoviet.tool;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;
import fr.dudie.nominatim.model.AddressElement;
import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.google.maps.model.LocationType;

/**
 * get linkedin profiles.
 * 
 * @author quocanh
 *
 */
@SuppressWarnings("deprecation")
@Service
public class LinkedInCrawler {
  class Coordination {
    Double lat;
    Double lng;
    String city;
    String country;

    public Coordination(Double lat, Double lng, String city, String country) {
      this.lat = lat;
      this.lng = lng;
      this.city = city;
      this.country = country;
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(LinkedInCrawler.class);

  @Autowired
  PlaceService placeService;

  List<String> linksInProgress = new ArrayList<String>();
  List<String> linksAlready = new ArrayList<String>();

  Map<String, Coordination> existingLocation = new HashMap<String, Coordination>();

  public void getDataFromLinkedin() {
    // linksInProgress = initSeeds();
    linksInProgress.add("https://fr.linkedin.com/in/vungocanh");
    linksInProgress.add("https://www.linkedin.com/pub/khuong-nguyen/31/2a7/5a");
    linksInProgress.add("https://www.linkedin.com/in/paulvinhphan");
    linksInProgress.add("https://www.linkedin.com/in/christophergphan");
    linksInProgress.add("https://www.linkedin.com/in/codeformoney");
    linksInProgress.add("https://www.linkedin.com/in/tamthaopham");
    // System.out.println("So luong seeds la: " + linksInProgress.size());
    boolean flag = true;
    while (!linksInProgress.isEmpty() && flag) {
      System.out.println("DA XU LY: " + linksAlready.size());
      System.out.println("CON LAI: " + linksInProgress.size());
      flag = false;
      try {
        Thread.sleep(1000); // 1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      List<String> newLst = new ArrayList<String>(linksInProgress);
      for (String u : newLst) {
        Place place = getPlace(u);
        linksAlready.add(u);
        linksInProgress.remove(u);
        if (place != null) {
          System.out.println(place.getLatitude() + "," + place.getLongitude());

          if (place.getAddress() == null || place.getLatitude() == null
              || place.getLongitude() == null || place.getCountry() == null
              || place.getTitle() == null) {
            System.out.print("Co loi tai: " + u);

          } else {
            try {
              flag = true;
              placeService.save(place, null);
            } catch (Exception e) {
              System.out.print("Co loi tai: " + e.getMessage());
            }
          }

        }
      }
    }

  }

  private List initSeeds() {

    List<String> urls = new ArrayList<String>();
    for (int i = 0; i < VietnameseWords.surnames.length; i++)
      for (int k = 0; k < VietnameseWords.VOCABULARY.split(" ").length; k++) {

        String url = "https://www.linkedin.com/pub/dir/?first="
            + VietnameseWords.VOCABULARY.split(" ")[k] + "&last=" + VietnameseWords.surnames[i]
            + "&search=Search&searchType=fps";
        Document doc;
        try {
          System.out.println("Dang xu ly: " + url);
          doc = Jsoup.connect(url)
              .userAgent(
                  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
              .get();

          Elements links = doc.select(".profile-card");
          // System.out.println("So luong vcard la: " + links.size());

          for (Element link : links) {
            String furl = link.select("a").attr("href");
            // System.out.println(furl);
            String ffullname = link.select("a img").attr("alt");
            if (ffullname.indexOf(",") > 0) {
              ffullname = ffullname.substring(0, ffullname.indexOf(","));
            }
            // System.out.println(ffullname);
            String photo = link.select("a img").attr("src");
            // System.out.println(photo);

            if (VietnameseWords.isVietnamese(ffullname) && photo.indexOf("ghosts") < 0) {
              // System.out.println("Day la ten tieng Viet: " + ffullname);
              if (!urls.contains(furl)) {
                urls.add(furl);
                // System.out.println("Them vao: " + furl);
              }
            }
          }
        } catch (IOException e) {
          return urls;
        }
      }
    // AMERICAN SURNAMES
    for (int i = 0; i < VietnameseWords.surnames.length; i++)
      for (int k = 0; k < VietnameseWords.AmericanNames.length; k++) {

        String url = "https://www.linkedin.com/pub/dir/?first=" + VietnameseWords.AmericanNames[k]
            + "&last=" + VietnameseWords.surnames[i] + "&search=Search&searchType=fps";
        Document doc;
        try {
          System.out.println("Dang xu ly: " + url);
          doc = Jsoup.connect(url)
              .userAgent(
                  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
              .get();

          Elements links = doc.select(".profile-card");
          // System.out.println("So luong vcard la: " + links.size());

          for (Element link : links) {
            String furl = link.select("a").attr("href");
            // System.out.println(furl);
            String ffullname = link.select("a img").attr("alt");
            if (ffullname.indexOf(",") > 0) {
              ffullname = ffullname.substring(0, ffullname.indexOf(","));
            }
            // System.out.println(ffullname);
            String photo = link.select("a img").attr("src");
            // System.out.println(photo);

            if (VietnameseWords.isVietnamese(ffullname) && photo.indexOf("ghosts") < 0) {
              // System.out.println("Day la ten tieng Viet: " + ffullname);
              if (!urls.contains(furl)) {
                urls.add(furl);
                // System.out.println("Them vao: " + furl);
              }
            }
          }
        } catch (IOException e) {
          return urls;
        }
      }
    return urls;
  }

  private Place getPlace(String url) { // id, title, address, country,latitude, longitude,
                                       // place_type
    System.out.println("DANG XU LY: " + url);
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setReferenceUrl(url);
    place.setPlaceType(PlaceType.INDIVIDUAL.toString());

    Document doc;
    try {
      // System.setProperty("http.proxyHost", "95.211.206.151");
      // System.setProperty("http.proxyPort", "80");
      // System.out.println("Dang xu ly: " + url);
      doc = Jsoup.connect(url).timeout(15000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();

      Elements name = doc.select("#name");
      String fullname = name.text();
      if (StringUtils.isEmpty(fullname)) {
        System.out.println("NAME is null");
        return null;
      }
      if (fullname.indexOf(",") > 0) {
        fullname = fullname.substring(0, fullname.indexOf(","));
      }

      place.setTitle(fullname);

      Elements title = doc.select(".profile-overview").select(".title");
      if (!title.isEmpty() && title.size() > 0) {
        String subtitle = title.get(0).text();
        place.setSubtitle(subtitle);
        place.setTitle(place.getTitle() + " - " + subtitle);
      }

      Elements picture = doc.select(".profile-picture");
      if (!picture.isEmpty() && picture.size() > 0) {
        String imagePath = picture.get(0).select("a img").attr("data-delayed-url");
        if (imagePath.isEmpty())
          imagePath = picture.get(0).select(".photo").attr("src");
        place.setImagePath(imagePath);
      }

      doc.select(".summary-header").remove();

      Elements information = doc.select(".profile-section");
      information.select("iframe").remove();
      String info = "";
      if (!information.isEmpty() && information.size() > 1) {
        for (int k = 1; k < information.size(); k++)
          info = info.concat(information.get(k).html());
      }

      /*
       * if (info.indexOf("Chinese") > 0) { return null; }
       */

      info = info.replace("<h2>Background</h2>", "");
      info = info.replace("h5", "h6");
      info = info.replace("h4", "h5");
      info = info.replace("h3", "h4");
      info = info.replace("Summary", "Giới thiệu");
      info = info.replace("Experience", "Kinh nghiệm làm việc");
      info = info.replace("Education", "Quá trình đào tạo");
      info = info.replace("Skills", "Kỹ năng");
      info = info.replace("Publications", "Công trình công bố");
      info = info.replace("Interests", "Sở thích cá nhân");
      place.setInformation(info);


      // if (address.indexOf("Vietnam") < 0) {

      Elements links = doc.select(".insights li");
      System.out.println(links.size());
      boolean vietnamese = false;
      for (Element link : links) {
        try {

          if (link.select("a").isEmpty())
            continue;
          String furl = link.select("a").get(0).attr("href");
          // System.out.println(furl);
          if (!link.select(".info").isEmpty() && link.select(".info").size() > 0) {
            String ffullname = link.select(".info").get(0).select("a").text();
            if (ffullname.indexOf(",") > 0) {
              ffullname = ffullname.substring(0, ffullname.indexOf(","));
            }

            // System.out.println(ffullname);
            String photo = link.select("a").get(0).select("img").attr("src");
            if (photo.isEmpty()) {
              photo = link.select("a").get(0).select("img").attr("data-delayed-url");
            }
           // System.out.println("photo: " + photo);

            if (VietnameseWords.isVietnamese(ffullname) ) {
              vietnamese = true;
              System.out.println("Day la ten tieng Viet: " + ffullname);
              if ((photo.indexOf("ghosts") < 0) && !linksInProgress.contains(furl) && !linksAlready.contains(furl)) {
                linksInProgress.add(linksInProgress.size(), furl);
                // System.out.println("Them vao: " + furl);
              }
            }
          }
        } catch (Exception e) {
          System.out.println(e.getMessage() + " : " + link.html());
        }
      }
      if (!vietnamese) {
        System.out.println("KHONG CO BAN CHUNG VIET");
        return null;
      }
      
      
      Elements locality = doc.select(".locality");
      if (locality.isEmpty() || locality.size() < 1) {
        locality = doc.select(".location");
        if (locality.isEmpty()) System.out.println("ADDRESS is null");
        return null;
      }
        
      String address = locality.get(0).html().replace("Area", "").replace("Greater ", "");

      /*
       * if (address.indexOf("Vietnam") >= 0) { return null; }
       */

      // Neu dia chi nay da duoc tinh toan sang lat, lng roi thi chi viec lay ra
      if (existingLocation.containsKey(address)) {
        System.out.println("Dia chi: " + address + " : da duoc tinh toan");
        Coordination result = existingLocation.get(address);
        place.setAddress(address);
        place.setLatitude(result.lat);
        place.setLongitude(result.lng);
        place.setCity(result.city);
        place.setCountry(result.country);

      }
      // neu khong phai tinh toan lai
      else {
        if (!getAddress(place, address)) {
          // neu dia chi ko ton tai thi stop
          System.out.println("ADDRESS is not found");
          return null;
        }
        // sau khi tinh toan thi them vao danh sach ton tai
        else {
          existingLocation.put(address, new Coordination(place.getLatitude(), place.getLongitude(),
              place.getCity(), place.getCountry()));
        }
      }

      // }
    } catch (IOException e) {
      System.out.println("BI LOI: "  + e.getMessage());
      return null;
    }
    return place;
  }

  public boolean getAddress(Place place, String location) {
    if (StringUtils.isEmpty(location)) {
      return false;
    }
    try {
      Thread.sleep(1000); // 1000 milliseconds is one second.
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    try {
      NominatimSearchRequest req = new NominatimSearchRequest();

      final SchemeRegistry registry = new SchemeRegistry();
      registry.register(new Scheme("http", new PlainSocketFactory(), 80));
      final ClientConnectionManager connexionManager = new SingleClientConnManager(null, registry);

      final HttpClient httpClient = new DefaultHttpClient(connexionManager, null);

      final String baseUrl = "http://nominatim.openstreetmap.org/";
      final String email = "quocanh263@gmail.com";
      JsonNominatimClient nominatimClient = new JsonNominatimClient(baseUrl, httpClient, email);

      try {
        List<Address> addresses = nominatimClient.search(location);
        for (final Address address : addresses) {

          System.out
              .println(ToStringBuilder.reflectionToString(address, ToStringStyle.MULTI_LINE_STYLE));
          if (address.getDisplayName().length() > 255)
            address.setDisplayName(location);
          place.setLatitude(address.getLatitude());
          place.setLongitude(address.getLongitude());

          place.setAddress(address.getDisplayName());

          if (address.getAddressElements() != null) {
            for (AddressElement element : address.getAddressElements()) {
              if (element.getKey().equalsIgnoreCase("country_code")) {
                place.setCountry(element.getValue().toUpperCase());
              }

              if (element.getKey().equalsIgnoreCase("city")) {
                place.setCity(element.getValue());
              }

              if (element.getKey().equalsIgnoreCase("postcode")) {
                place.setPostalCode(element.getValue());
              }
            }
          } else {

            try {
              NominatimReverseGeocodingJAPI nominatim = new NominatimReverseGeocodingJAPI();
              NominatimAddress add = nominatim.getAdress(place.getLatitude(), place.getLongitude());
              if (add != null) {
                if (add.getCity() != null)
                  place.setCity(add.getCity());
                if (add.getPostcode() != null)
                  place.setPostalCode(add.getPostcode());
                if (add.getCountry() != null)
                  place.setCountry(add.getCountryCode().toUpperCase());
              }

            } catch (Exception e) {
              break;
            }

          }

          break;
        }
      } catch (IOException e) {
        System.out.println("Co loi nominatimClient: " + e.getMessage());

      }
    } catch (Exception e) {
      System.out.println("Co loi NominatimSearchRequest: " + e.getMessage());
    }

    if (place.getLatitude() == null || place.getLongitude() == null || place.getCountry() == null) {
      getAddressFromGoogleMap(place, location);
    }

    return (place.getLatitude() != null && place.getLongitude() != null
        && place.getCountry() != null);
  }

  public boolean getAddressFromGoogleMap(Place place, String location) {

    if (StringUtils.isEmpty(location)) {
      return false;
    }

    GeoApiContext context = new GeoApiContext()
        .setApiKey("AIzaSyCJbKbcTqdaVh5oJVTOBTHPaBDViLurLxM");
    GeocodingResult[] results;
    try {
      results = GeocodingApi.geocode(context, location).await();
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

  public String getUrlSource(String url) throws IOException {
    URL site = new URL(url);
    URLConnection yc = site.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
    String inputLine;
    StringBuilder str = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
      str.append(inputLine);
    }
    in.close();

    return str.toString();
  }

  public void getLinks(String html) {
    int idx = 0;
    int end = 0;
    while (idx < html.length()) {

      String inputLine = html.substring(idx);
      int next = -1;
      int in = inputLine.indexOf("https://www.linkedin.com/in/");
      int pub = inputLine.indexOf("https://www.linkedin.com/pub/");

      if (in < pub && 0 <= in) {
        next = in;
      } else {
        next = pub;
      }

      if (next >= 0) {
        end = 0;
        int j = inputLine.indexOf('"', next + 1);
        int k = inputLine.indexOf("'", next + 1);
        if (j < k && 0 < j) {
          end = j;
        } else if (j > k && k > 0) {
          end = k;
        }
        String s = inputLine.substring(next, end);
        if (!linksInProgress.contains(s) && !linksAlready.contains(s)) {
          linksInProgress.add(s);
        }
      } else {
        break;
      }

      idx = idx + "https://www.linkedin.com/pub/".length();
    }
  }

  public static List<String> getLinks_(String a) {
    List<String> lstLinks = new ArrayList<String>();
    URL url;
    try {
      // get URL content

      // String a="https://fr.foursquare.com/explore?mode=url&near=Californie&q=pho";
      // String a = "http://annuairesante.ameli.fr/";
      url = new URL(a);
      URLConnection conn = url.openConnection();

      // open the stream and put it into BufferedReader
      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      String inputLine;
      StringBuffer html = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        html.append(inputLine);
      }
      br.close();

      int idx = 0;
      int end = 0;
      while (idx < html.length()) {

        inputLine = html.substring(idx);
        int i = inputLine.indexOf("https://www.linkedin.com/in/");

        if (i >= 0) {
          end = 0;
          int j = inputLine.indexOf('"', i + 1);
          int k = inputLine.indexOf("'", i + 1);
          if (j < k && 0 < j) {
            end = j;
          } else if (j > k && k > 0) {
            end = k;
          }
          String s = inputLine.substring(i, end);
          if (!lstLinks.contains(s))
            lstLinks.add(s);
        }

        i = inputLine.indexOf("https://www.linkedin.com/pub/");

        if (i >= 0) {
          end = 0;
          int j = inputLine.indexOf('"', i + 1);
          int k = inputLine.indexOf("'", i + 1);
          if (j < k && 0 < j) {
            end = j;
          } else if (j > k && k > 0) {
            end = k;
          }
          String s = inputLine.substring(i, end);

          if (!lstLinks.contains(s))
            lstLinks.add(s);
        }

        if (i < 0) {
          break;
        }

        idx = idx + "https://www.linkedin.com/pub/".length();
      }

      System.out.println("Done");

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return lstLinks;
  }

}
