package net.bandoviet.place;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TestLinkedIn {
  

  public static void main(String[] args) throws Exception {

    System.out.println("ok");

    for (String s : getLinks("https://www.linkedin.com/pub/tuan-anh-phan/b1/700/375?trk=pub-pbmap")) {
      if (s.indexOf("dir") > 0) {
        System.out.println("DIR: " + s);
      } else {
        System.out.println(s);
        getLinks(s);
      }

    }
  }
  
  public static String getName(String s) {
    int start = s.indexOf("full-name");
    start = s.indexOf(">", start) + 1;
    int end = s.indexOf("</span>", start);
    String name = s.substring(start, end) ;    
    return name;
  }

  public static Place getProfile(String s) {
    Place place = new Place();
    Document doc = Jsoup.parse(s);

    Elements name = doc.select(".full-name");
    if (name.isEmpty()) return null;
    
    System.out.println(name.text());
    Elements title = doc.select(".title");
    System.out.println(title.text());
    
    Elements picture = doc.select(".profile-picture").select("img");
    System.out.println(picture.get(0).attr("src"));
    
    Elements information = doc.select("#background");
    System.out.println(information.html());
    
    Elements locality = doc.select(".locality");
    System.out.println(locality.get(0).html());
    return place;
  }

  public static List<String> getLinks(String a) {
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
      
      getProfile(html.toString());
      
      int idx = 0;
      int end = 0;
      while (idx < html.length()) {

        inputLine = html.substring(idx);
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
          if (!lstLinks.contains(s))
            lstLinks.add(s);
        } else {
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
