package net.bandoviet.tool;

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
import java.util.List;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;
import fr.dudie.nominatim.model.AddressElement;
import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;

/**
 * get linkedin profiles.
 * 
 * @author quocanh
 *
 */
@Service
public class LinkedInCrawler {
  @Autowired
  PlaceService placeService;

  List<String> linksInProgress = new ArrayList<String>();
  List<String> linksAlready = new ArrayList<String>();

  public void getDataFromLinkedin() {
    String url = "https://www.linkedin.com/in/anphungkhac";
    linksInProgress.add(url);

    while (true) {
      try {
        Thread.sleep(1000); // 1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      List<String> newLst = new ArrayList<String>(linksInProgress);
      for (String u : newLst) {
        Place place = getPlace(u);
        if (place != null) {
          System.out.println(place.getLatitude() + "," + place.getLongitude());
          linksAlready.add(u);
          linksInProgress.remove(u);
          placeService.save(place, null);
        }
      }
    }

  }

  private Place getPlace(String url) { // id, title, address, country,latitude, longitude,
                                       // place_type
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setReferenceUrl(url);
    place.setPlaceType(PlaceType.INDIVIDUAL.toString());

    Document doc;
    try {
      doc = Jsoup.connect(url).get();

      Elements name = doc.select(".full-name");
      String fullname = name.text();
      if (StringUtils.isEmpty(fullname)) {
        return null;
      }
      place.setTitle(fullname);

      Elements title = doc.select(".title");
      String subtitle = title.text();
      place.setSubtitle(subtitle);
      place.setTitle(place.getTitle() + " - " + subtitle);

      Elements picture = doc.select(".profile-picture").select("img");
      String imagePath = picture.get(0).attr("src");
      place.setImagePath(imagePath);

      Elements information = doc.select("#background");
      String info = information.html();
      place.setInformation(info);

      Elements locality = doc.select(".locality");
      String address = locality.get(0).html().replace("Area", "");
      System.out.println(address);
      if (!getAddress(place, address)) {
        return null;
      }

      Elements links = doc.select(".insights-browse-map ul li");
      System.out.println(links.size());
      for (Element link : links) {
        String furl = link.select("h4 a").attr("href");
        System.out.println(furl);
        String ffullname = link.select("h4 a").text();
        System.out.println(ffullname);
        String photo = link.select("a img").attr("data-li-src");
        System.out.println(photo);

        if (VietnameseWords.isVietnamese(ffullname) && photo.indexOf("ghosts") < 0) {
          System.out.println("Day la ten tieng Viet: " + ffullname);
          if (!linksInProgress.contains(furl) && !linksAlready.contains(furl)) {
            linksInProgress.add(furl);
          }
        }
      }
    } catch (IOException e) {
      return null;
    }
    return place;
  }

  private boolean getAddress(Place place, String location) {
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
            Thread.sleep(1000); // 1000 milliseconds is one second.
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          NominatimReverseGeocodingJAPI nominatim = new NominatimReverseGeocodingJAPI();
          NominatimAddress add = nominatim.getAdress(place.getLatitude(), place.getLongitude());
          place.setCity(add.getCity());
          place.setPostalCode(add.getPostcode());
          place.setCountry(add.getCountryCode().toUpperCase());
        }

        break;
      }
    } catch (IOException e) {
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
