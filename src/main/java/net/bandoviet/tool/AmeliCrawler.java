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

import java.io.File;
import java.io.IOException;



@Service
public class AmeliCrawler {
  
  @Autowired
  LinkedInCrawler tool;
  
  @Autowired
  PlaceService placeService;
  
  public void getDataFromAmeli() {
    File folder = new File("/Users/quocanh/Documents/ws/data/ameli/");
    File[] listOfFiles = folder.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile() && listOfFiles[i].getName().indexOf(".html") > 0) {
        System.out.println("File " + listOfFiles[i].getName());
        Place place = getPlace(listOfFiles[i].getAbsolutePath());
        if (place != null)
          placeService.save(place, null);
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }    
  }

  private Place getPlace(String path) {
    Place place = new Place();
    place.setCommunityCode("VN");
    place.setPlaceType(PlaceType.HEALTHCARE.toString());
    
    File input = new File(path);
    try {
      Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
      Elements items = doc.select(".item-professionnel");
      for (Element item : items) {
        String name = item.select("h2 a").html();
        name = name.replace("<strong>", "");
        name = name.replace("</strong>", "");
        
        
        if (VietnameseWords.isVietnamese(name)) {
          System.out.println(name);
          place.setTitle(name);
          
          // PHOTO
          if (doc.select(".pictos img").attr("alt").equalsIgnoreCase("Accepte la carte Vitale")) {
            System.out.println("Accepte la carte Vitale");
            String photo = "http://medi94belleepine.chez.com/images/carte.jpg";
            place.setImagePath(photo);
          } else {
            System.out.println("NONONONONO: Accepte la carte Vitale");
          }
          
          // SPECIALITY
          Elements speciality = item.select(".specialite");
          System.out.println(speciality.text());
          place.setTitle(place.getTitle() + " - " + speciality.text());
          
          // tEL
          Elements tel = item.select(".tel");
          System.out.println(tel.text());
          place.setTelephone(tel.text());
          
          // TARIF
          Elements convention = item.select(".convention");
          System.out.println(convention.text());
          place.setInformation(convention.text());
          
          // ADDRESS
          Elements address = item.select(".adresse");
          System.out.println(address.text());
          
          String add = address.text();
          int i;
          for (i = 0; i < add.length(); i++) {
            if (Character.isDigit(add.charAt(i))) {
              break;
            }
          }
          add = add.substring(i);
          
          if (!tool.getAddress(place, add)) {
            return null;
          }
          
        } else {
          System.out.println("NO VIETNAMESE: " + name);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Co loi: " + e.getMessage());
      return null;
    }
    return place;
  }
}
