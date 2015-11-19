package net.bandoviet.crawl;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import net.bandoviet.country.Country;
import net.bandoviet.country.CountryService;
import net.bandoviet.place.Place;
import net.bandoviet.place.PlaceService;
import net.bandoviet.place.PlaceType;
import net.bandoviet.tool.StringTools;

@Service
public class YelpCrawler {

  @Autowired
  PlaceService placeService;
  @Autowired
  CountryService countryService;

  List<String> linksInProgress = new ArrayList<String>();
  List<String> linksAlready = new ArrayList<String>();
  Hashtable<Integer, Double> latitudes = new Hashtable<Integer, Double>();
  Hashtable<Integer, Double> longitudes = new Hashtable<Integer, Double>();

  
  private String getHtml (String ip, int port, String link) throws IOException {
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
    URL oracle = new URL(link);
    URLConnection yc = oracle.openConnection(proxy);
    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
    String inputLine;
 
    StringBuffer result = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      //System.out.println(inputLine);
      result.append(inputLine);
    }
      
    in.close();

    return result.toString();
  }

  public void run() {
    Hashtable<String, String> seeds = new Hashtable<String, String>();
    seeds.put("CA", "Toronto, ON, Canada");
    seeds.put("US", "Seattle, WA");
    seeds.put("US", "San Jose, CA");
    seeds.put("US", "San Francisco, CA");
    seeds.put("US", "San Diego, CA");
    seeds.put("US", "Saint Louis, MO");
    seeds.put("US", "Portland, OR");
    seeds.put("US", "Philadelphia, PA");
    seeds.put("US", "Palo Alto, CA");
    seeds.put("US", "Oakland, CA");
    seeds.put("US", "Minneapolis, MN");
    seeds.put("US", "Miami, FL");
    seeds.put("ES", "Madrid, Spain");
    seeds.put("US", "Las Vegas, NV");
    seeds.put("US", "Houston, TX");
    seeds.put("US", "Honolulu, HI");
    seeds.put("IE", "Dublin, Ireland");
    seeds.put("US", "Detroit, MI");
    seeds.put("US", "Denver, CO");
    seeds.put("US", "Dallas, TX");
    seeds.put("US", "Chicago, IL");
    seeds.put("US", "Boston, MA");
    seeds.put("US", "Austin, TX");
    seeds.put("US", "Atlanta, GA");
    seeds.put("IT", "Beinasco, Torino, Italy");
    seeds.put("DE", "Aachen, Nordrhein-Westfalen, Germany");
    seeds.put("PL", "Polanka Wielka, Małopolskie, Poland");
    seeds.put("NL", "Tilburg");

    Enumeration<String> enumKey = seeds.keys();
    while (enumKey.hasMoreElements()) {
      String key = enumKey.nextElement();
      String val = seeds.get(key);
      run(key, val);
    }
  }

  public void run1() {
    List<Country> countries = countryService.getListCountry();

    for (Country country : countries) {
      if (!country.getCapital().equalsIgnoreCase("Paris")
          && !country.getCapital().equalsIgnoreCase("Tokyo")
          && !country.getCapital().equalsIgnoreCase("Berlin")
          && !country.getCapital().equalsIgnoreCase("Amsterdam")
          && StringUtils.isNotBlank(country.getCapital())) {
        run(country.getCode(), country.getCapital());
      }

    }
  }

  public void run(String code) {
    String csvFile = "/Users/quocanh/worldcities.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    try {

      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {

        // use comma as separator
        String[] country = line.split(cvsSplitBy);

        System.out.println(country[0].replaceAll("\"", "") + " : "
            + country[6].replaceAll("\"", "").replaceAll(" ", "+"));
        String cd = country[0].replaceAll("\"", "");
        if ((code.equalsIgnoreCase(cd))) {
          run(code, country[6].replaceAll("\"", "").replaceAll(" ", "+"));
        }

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    System.out.println("Done");
  }

  public void run(String country, String city) {
    String url = "http://www.yelp.com/search?find_desc=vietnamese&find_loc=" + city + "&start=0";

    linksInProgress.add(url);
    while (!linksInProgress.isEmpty()) {
      System.out.println("Con lai: " + linksInProgress.size());
      String link = linksInProgress.remove(0);
      if (linksAlready.indexOf(link) < 0) {
        linksAlready.add(link);
        System.out.println("DANG XU LY: " + link);
        getPage(link, country, city);
      }

      try {
        Thread.sleep(5000); // 1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }
  
  public void run(String country, String city, String ip, int port) {
    String url = "http://www.yelp.com/search?find_desc=vietnamese&find_loc=" + city + "&start=0";

    linksInProgress.add(url);
    while (!linksInProgress.isEmpty()) {
      System.out.println("Con lai: " + linksInProgress.size());
      String link = linksInProgress.remove(0);
      if (linksAlready.indexOf(link) < 0) {
        linksAlready.add(link);
        System.out.println("DANG XU LY: " + link);
        getPage(link, country, city, ip, port);
      }

      try {
        Thread.sleep(5000); // 1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }
  private void getPage(String url, String country, String city, String ip, int port) {

    try {
      String html = this.getHtml(ip, port, url); 
      Document doc = Jsoup.parse(html);// Jsoup.connect(url).timeout(10000)
          
      // Document doc = Jsoup.connect(url).get();
      Elements items = doc.select(".regular-search-result");

      if (items == null || items.size() < 1) {
        return;
      }

      System.out.println(items.size());

      getCoordinations(doc);

      for (int i = 0; i < items.size(); i++) {
        Place place = getPlace(items.get(i));
        if (place != null) {
          place.setCity(city);
          place.setCountry(country);
          place.setLatitude(latitudes.get(Integer.valueOf(place.getIdLookitUrl())));
          place.setLongitude(longitudes.get(Integer.valueOf(place.getIdLookitUrl())));

          if (place.getLatitude() != null && place.getLongitude() != null) {
            Place result = placeService.save(place, null);
            if (result == null) {
              return;
            }
          }

        }
      }

      // lay duong linkk trang ke tiep
      Elements links = doc.select(".search-pagination").select("li a");
      for (Element link : links) {
        String add = "http://www.yelp.com" + link.attr("href");
        if (!linksInProgress.contains(add) && !linksAlready.contains(add)) {
          linksInProgress.add(add);
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void getPage(String url, String country, String city) {

    try {
      Document doc = Jsoup.connect(url).timeout(10000)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
          .get();
      // Document doc = Jsoup.connect(url).get();
      Elements items = doc.select(".regular-search-result");

      if (items == null || items.size() < 1) {
        return;
      }

      System.out.println(items.size());

      getCoordinations(doc);

      for (int i = 0; i < items.size(); i++) {
        Place place = getPlace(items.get(i));
        if (place != null) {
          place.setCity(city);
          place.setCountry(country);
          place.setLatitude(latitudes.get(Integer.valueOf(place.getIdLookitUrl())));
          place.setLongitude(longitudes.get(Integer.valueOf(place.getIdLookitUrl())));

          if (place.getLatitude() != null && place.getLongitude() != null) {
            Place result = placeService.save(place, null);
            if (result == null) {
              return;
            }
          }

        }
      }

      // lay duong linkk trang ke tiep
      Elements links = doc.select(".search-pagination").select("li a");
      for (Element link : links) {
        String add = "http://www.yelp.com" + link.attr("href");
        if (!linksInProgress.contains(add) && !linksAlready.contains(add)) {
          linksInProgress.add(add);
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void getCoordinations(Document doc) {

    // Lay toa do (lat, lng) cac POIs
    String key1 = "yelp.www.init.search.Controller(";
    String key2 = "#find_desc";
    String str = doc.html();

    int start = str.indexOf(key1) + key1.length();
    int end = str.indexOf(key2, start);

    String json = str.substring(start, end);

    // System.out.println(json);

    int idx = 0;
    while (json.indexOf("key", idx + 3) > 0) {
      int position = json.indexOf("key", idx + 3);
      String numStr = json.substring(position + 5, json.indexOf(",", position + 5)).trim();
      if (!StringTools.isNumeric(numStr)) {
        return;
      }
      // System.out.println(String.valueOf(numStr));
      Integer key = Integer.valueOf(numStr);

      String substr = json.substring(idx, position);
      // System.out.println(substr);
      start = substr.lastIndexOf("latitude") + 11;
      // System.out.println(start);
      end = substr.indexOf(",", start);

      // System.out.println(substr.substring(start, end));
      Double latitude = Double.valueOf(substr.substring(start, end));

      start = substr.lastIndexOf("longitude") + 12;
      end = substr.indexOf("}", start);
      // System.out.println(substr.substring(start, end));
      Double longitude = Double.valueOf(substr.substring(start, end));

      latitudes.put(key, latitude);
      longitudes.put(key, longitude);

      idx = json.indexOf("key", idx + 3);
    }
  }

  private Place getPlace(Element item) {

    Place place = new Place();
    place.setCommunityCode("VN");
    // place.setReferenceUrl(url);
    place.setPlaceType(PlaceType.RESTAURANT.toString());

    String key = item.select(".natural-search-result").attr("data-key");
    place.setIdLookitUrl(key);
    // System.out.println(key);

    String photo = "http:" + item.select(".photo-box-img").attr("src");
    place.setImagePath(photo);
    // System.out.println(photo);

    String title = item.select(".photo-box-img").attr("alt");
    place.setTitle(title);
    // System.out.println(title);

    String telephone = item.select(".biz-phone").get(0).text();
    place.setTelephone(telephone);
    // System.out.println(telephone);

    String address = item.select("address").text();
    place.setAddress(address);
    // System.out.println(address);

    if (photo == null || title == null || address == null) {
      return null;
    } else {
      return place;
    }

  }
}
/*
 * Con lai: 4 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Washington+D.C.&start=180 HTTP error
 * fetching URL Con lai: 3 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Washington+D.C.&start=190 HTTP error
 * fetching URL Con lai: 2 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Washington+D.C.&start=200 HTTP error
 * fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Washington+D.C.&start=210 HTTP error
 * fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Montevideo&start=0 Con lai: 1 DANG XU
 * LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Tashkent&start=0 HTTP error fetching
 * URL Con lai: 1 DANG XU LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Vatican
 * City&start=0 Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Kingstown&start=0 HTTP error fetching
 * URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Caracas&start=0 Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Road Town&start=0 HTTP error fetching
 * URL Con lai: 1 DANG XU LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Charlotte
 * Amalie&start=0 HTTP error fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Hanoi&start=0 HTTP error fetching URL
 * Con lai: 1 DANG XU LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Port Vila&start=0
 * HTTP error fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Mata-Utu&start=0 HTTP error fetching URL
 * Con lai: 1 DANG XU LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Apia&start=0 HTTP
 * error fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Pristina&start=0 HTTP error fetching URL
 * Con lai: 1 DANG XU LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Sana'a&start=0
 * HTTP error fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Mamoudzou&start=0 HTTP error fetching
 * URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Pretoria&start=0 HTTP error fetching URL
 * Con lai: 1 DANG XU LY: http://www.yelp.com/search?find_desc=vietnamese&find_loc=Lusaka&start=0
 * HTTP error fetching URL Con lai: 1 DANG XU LY:
 * http://www.yelp.com/search?find_desc=vietnamese&find_loc=Harare&start=0 HTTP error fetching URL
 */