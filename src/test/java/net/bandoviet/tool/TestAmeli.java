package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

import net.bandoviet.place.PlaceService;

public class TestAmeli {
  
  @Test
  public void test() {
    File folder = new File("/Users/quocanh/Documents/ws/data/ameli/");
    File[] listOfFiles = folder.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile() && listOfFiles[i].getName().indexOf(".html") > 0) {
        System.out.println("File " + listOfFiles[i].getAbsolutePath());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }

    File input = new File("/Users/quocanh/Documents/ws/data/ameli/113.html");
    try {
      Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
      Elements items = doc.select(".item-professionnel");
      for (Element item : items) {
        String name = item.select("h2 a").html();
        name = name.replace("<strong>", "");
        name = name.replace("</strong>", "");
        
        
        if (VietnameseWords.isVietnamese(name)) {
          System.out.println(name);
          
          // PHOTO
          if (doc.select(".pictos img").attr("alt").equalsIgnoreCase("Accepte la carte Vitale")) {
            System.out.println("Accepte la carte Vitale");
            String photo = "http://medi94belleepine.chez.com/images/carte.jpg";
          } else {
            System.out.println("NONONONONO: Accepte la carte Vitale");
          }
          
          // SPECIALITY
          Elements speciality = item.select(".specialite");
          System.out.println(speciality.text());
          
          // tEL
          Elements tel = item.select(".tel");
          System.out.println(tel.text());
          
          // TARIF
          Elements convention = item.select(".convention");
          System.out.println(convention.text());
          
          
          // ADDRESS
          Elements address = item.select(".adresse");
          System.out.println(address.text());
          
        } else {
          System.out.println("NO VIETNAMESE: " + name);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("ok");
  }
}
