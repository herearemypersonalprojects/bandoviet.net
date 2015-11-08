package net.bandoviet.linkedin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * To get all person names in each country.
 * @author quocanh
 *
 */
@Service
public class LinkedInService {
  private static final Logger LOGGER = LoggerFactory.getLogger(LinkedInService.class);
  //public static long count = 0;
  @Autowired LinkedInRepository repository;
  
  public void save(LinkedIn linkedIn) {
    //count++;
    //System.out.println("Tong cong: " + count);
    repository.save(linkedIn);
  }
  
  public void save(String url, String name, String country, Float foundTimes) {
    LinkedIn linkedIn = new LinkedIn();
    linkedIn.setUrl(url);
    linkedIn.setName(name);
    linkedIn.setCountry(country);
    linkedIn.setFoundTimes(foundTimes);
    
    save(linkedIn);
  }
  
  public void crawlTest() {
    String url = "https://vn.linkedin.com/pub/dir/Ngoc%20anh/Nguyen";
    try {
      Document doc = Jsoup.connect(url).get();

      Elements linkresult = doc.select(".profile-card");
      //System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String item = link.select(".content h3 a").text();
        String href = link.select(".content h3 a").attr("href");
       
        //System.out.println("Bo vao CSDL: " + href + " : " + item);
       

      }

    } catch (IOException e) {
      LOGGER.error("Co loi: " + e.getMessage() + " : " + url);
    }
  }
  
  /**
   * Bat dau crawl tu day.
   */
  public void crawl() {
    getListAlphabet123("https://vn.linkedin.com/", "Vietnam");
    
    getListAlphabet123("https://fr.linkedin.com/", "France");  
    
    getListAlphabet123("https://www.linkedin.com/", "United States");
    
    getCountry();
  }
  
  /**
   * Lay danh sach cach nuoc va loc the tung nuoc.
   */
  public void getCountry() {
    String url = "https://www.linkedin.com/directory/country_listing/?trk=uno-reg-guest-home-country";
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements linkresult = doc.select(".content");
      //System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String text = link.select("a").text();
        String country = text.substring(0, text.indexOf(" -"));

        String href = link.select("a").attr("href");
        //System.out.println(href + " : " + country);
        if (!country.equalsIgnoreCase("Vietnam") && !country.equalsIgnoreCase("France")) {
          getListAlphabet123(href, country);
        }
        
      }
      
      
    } catch (IOException e) {
      LOGGER.error("Co loi: " + e.getMessage() + " : " + url);
    }
  }

  /**
   * Lay danh sach ten theo chu cai dau khong dau hoac co dau.
   */
  public void getListAlphabet123(String countryUrl, String country) {
    //String countryUrl = "https://vn.linkedin.com/"; 
    String url = countryUrl + "directory/people-a/";
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      Elements linkresult = doc.select(".bucket-item");
      //System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String item = link.select("a").text();
        String href = link.select("a").attr("href");
        if (item.isEmpty()) {
          item = link.text();
          href = url;
        }

        //System.out.println(href + " : " + item);
        getNames(href, country, countryUrl);
      }
    } catch (IOException e) {
      LOGGER.error("Co loi: " + e.getMessage() + " : " + url);
    }
  }

 
  /**
   * Lap lai qua trinh tim theo chu cai dau cua ten.
   */
  public void getNames(String url, String country, String countryUrl) {
    //String url = "https://vn.linkedin.com/directory/people-a/";
    //String country = "Vietnam";
    //String countryUrl = "https://vn.linkedin.com/"; 
    findNames(url, country, countryUrl);
  }

  /**
   * Lap lai qua trinh tim theo chu cai dau cua ten.
   */
  private void findNames(String url, String country, String countryUrl) {
    try {
      Thread.sleep(1000); // 1000 milliseconds is one second.
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    try {
      Document doc = Jsoup.connect(url).get();

      Elements linkresult = doc.select(".content");
      //System.out.println("Tong cong: " + linkresult.size());
      for (Element link : linkresult) {
        String item = link.select("a").text();
        String href = link.select("a").attr("href");
         //System.out.println(href + " : " + item);
        if (href.indexOf("directory/people") > 0) {
          findNames(href, country, countryUrl);
        } else if (href.indexOf("pub/dir/") >= 0) {
          findDir(countryUrl + href, country);  
        } else {
          //System.out.println("Bo vao CSDL: " + href + " : " + item + " : " + country);
          try {
            save( href,  item,  country, 1.0f);
          } catch (Exception e) {
            LOGGER.error("Co loi save: " + e.getMessage() + " : " + href + "-" + item + "-" + country);
          }
        }

      }

    } catch (IOException e) {
      LOGGER.error("Co loi: " + e.getMessage() + " : " + url);
    }
  }
  
  private void findDir(String url, String country) {  
   
    try {
      Document doc = Jsoup.connect(url).get();
      
      String text = doc.select(".join-banner").select(".content").select(".hide-mobile").text();
      text = text.replaceAll(",", "");
      
      Elements linkresult = doc.select(".profile-card");
      //System.out.println("Tong cong: " + linkresult.size());
      
      Pattern pattern = Pattern.compile("-?\\d+");
      Matcher match = pattern.matcher(text);
      Float foundTimes = 1.0f;
      while (match.find()) {
        float total = Float.valueOf(match.group());
        foundTimes =  total / linkresult.size();
        break;
      }   
      //System.out.println(foundTimes);
      
      for (Element link : linkresult) {
        String item = link.select(".content h3 a").text();
        String href = link.select(".content h3 a").attr("href");
       
        //System.out.println("Bo vao CSDL tu dir: " + href + " : " + item + " : " + country);
        
        try {
          save( href,  item,  country, foundTimes);
        } catch (Exception e) {
          LOGGER.error("Co loi save: " + e.getMessage() + " : " + href + "-" + item + "-" + country);
        }
      }

    } catch (IOException e) {
      LOGGER.error("Co loi: " + e.getMessage() + " : " + url);
    }
  }
}
