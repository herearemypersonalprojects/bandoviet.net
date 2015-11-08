package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLinkedin {

  @Test
  public void test() {
    String url = "https://vn.linkedin.com/pub/dir/?first=A&last=D";
    findDir( url, "Vietnam"); 
  }
  public void getCountry() {
    String url = "https://www.linkedin.com/directory/country_listing/?trk=uno-reg-guest-home-country";
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements linkresult = doc.select(".content");
      System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String text = link.select("a").text();
        String country = text.substring(0, text.indexOf(" -"));

        String href = link.select("a").attr("href");
        System.out.println(href + " : " + country);
        
        getListAlphabet123(href, country);
      }
      
      getListAlphabet123("https://www.linkedin.com/", "United States");
    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    }
  }

  public void getListAlphabet123(String countryUrl, String country) {
    //String countryUrl = "https://vn.linkedin.com/"; 
    String url = countryUrl + "directory/people-a/";
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements linkresult = doc.select(".bucket-item");
      System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String item = link.select("a").text();
        String href = link.select("a").attr("href");
        if (item.isEmpty()) {
          item = link.text();
          href = url;
        }

        System.out.println(href + " : " + item);
        getNames(href, country, countryUrl);
      }
    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    }
  }

 
  public void getNames(String url, String country, String countryUrl) {
    //String url = "https://vn.linkedin.com/directory/people-a/";
    //String country = "Vietnam";
    //String countryUrl = "https://vn.linkedin.com/"; 
    findNames(url, country, countryUrl);
  }

  private void findNames(String url, String country, String countryUrl) {
    try {
      Thread.sleep(1000); // 1000 milliseconds is one second.
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    try {
      Document doc = Jsoup.connect(url).get();

      Elements linkresult = doc.select(".content");
      System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String item = link.select("a").text();
        String href = link.select("a").attr("href");
         System.out.println(href + " : " + item);
        if (href.indexOf("directory/people") > 0) {
          findNames(href, country, countryUrl);
        } else if (href.indexOf("/pub/dir/") > 0) {
          findDir(countryUrl + href, country);  
        } else {
          System.out.println("Bo vao CSDL: " + href + " : " + item + " : " + country);
        }

      }

    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage() + " : " + url);
    }
  }
  
  public void findDir(String url, String country) {  
    try {
      Document doc = Jsoup.connect(url).get();
      String text = doc.select(".join-banner").select(".content").select(".hide-mobile").text();
      text = text.replaceAll(",", "");
      
      System.out.println(text);
      
      Elements linkresult = doc.select(".profile-card");
      System.out.println("Tong cong: " + linkresult.size());
      
      Pattern p = Pattern.compile("-?\\d+");
      Matcher m = p.matcher(text);
      Float foundTimes = 1.0f;
      while (m.find()) {
        float total = Float.valueOf(m.group());
        foundTimes =  total / linkresult.size();
        break;
      }   
      System.out.println(foundTimes);
      
      
      for (Element link : linkresult) {
        String item = link.select(".content h3 a").text();
        String href = link.select(".content h3 a").attr("href");
       
        System.out.println("Bo vao CSDL: " + href + " : " + item + " : " + country);
       

      }

    } catch (IOException e) {
      System.out.println("Co loi: " + e.getMessage());
    }
  }
}
