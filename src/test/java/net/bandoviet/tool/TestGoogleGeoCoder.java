package net.bandoviet.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;

/**
 * @author quocanh
 *
 */

public class TestGoogleGeoCoder {

  @Test
  public void testGoogleSearch() throws UnsupportedEncodingException, IOException {
    String google = "http://www.google.com/search?q=";
    String search = "nguyen+linkedin";
    String charset = "UTF-8";
    String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's
                                                                   // name and bot homepage!

    Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent)
        .get().select("li.g>h3>a");

    for (Element link : links) {
      String title = link.text();
      String url = link.absUrl("href"); // Google returns URLs in format
                                        // "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
      url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

      if (!url.startsWith("http")) {
        continue; // Ads/news/etc.
      }

      System.out.println("Title: " + title);
      System.out.println("URL: " + url);
    }
    
  }

  public void test2() {
    try {
      final Geocoder geocoder = new Geocoder();
      GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Paris, France")
          .setLanguage("en").getGeocoderRequest();
      GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

      List<GeocoderResult> results = geocoderResponse.getResults();

      if (results != null && !results.isEmpty()) {
        float latitude = results.get(0).getGeometry().getLocation().getLat().floatValue();

        float longitude = results.get(0).getGeometry().getLocation().getLng().floatValue();

        String address = results.get(0).getFormattedAddress();

        String country = "";
        for (GeocoderAddressComponent e : results.get(0).getAddressComponents()) {
          if (e.getTypes().contains("country")) {
            country = e.getShortName();
            break;
          }
        }

        System.out.println(latitude + "," + longitude + ": " + address + " -- " + country);

      }

    } catch (IOException e) {
      System.out.println("Co loi tu google geocoder: " + e.getMessage());
    }
  }

  public void test() {
    // Replace the API key below with a valid API key.
    GeoApiContext context = new GeoApiContext()
        .setApiKey("AIzaSyCJbKbcTqdaVh5oJVTOBTHPaBDViLurLxM");
    GeocodingResult[] results;
    try {
      results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway Mountain View, CA 94043")
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
      }

    } catch (Exception e1) {
      System.out.println(e1.getMessage());
    }
  }
}
