package net.bandoviet.place;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;



import java.net.HttpURLConnection;

public class GetDataFoursquare {
  
 
  public static void main(String[] args) throws Exception {

    //sendPost();
    
    test("https://www.linkedin.com/pub/nhu-cuong-tran/34/590/a02");
    //test("https://www.linkedin.com/in/vietdao");


  }
  
  private static void testOSM() throws IOException {
    NominatimSearchRequest req = new NominatimSearchRequest();
    req.setQuery("vitré, france");
    
    System.out.println(req.getQueryString());
    
    final SchemeRegistry registry = new SchemeRegistry();
    registry.register(new Scheme("http", new PlainSocketFactory(), 80));
    final ClientConnectionManager connexionManager = new SingleClientConnManager(null, registry);

    final HttpClient httpClient = new DefaultHttpClient(connexionManager, null);
    

    final String baseUrl = "http://nominatim.openstreetmap.org/";
    final String email = "xxx@gmail.com";
    JsonNominatimClient nominatimClient = new JsonNominatimClient(baseUrl, httpClient, email);
    
    Address add = nominatimClient.getAddress(1.64891269513038, 48.1166561643464);
    
    
    
     List<Address> addresses = nominatimClient.search("40 rue pascal, Paris, France");
    for (final Address address : addresses) {
      System.out.println(ToStringBuilder
              .reflectionToString(address, ToStringStyle.MULTI_LINE_STYLE));
      System.out.println(address.getLatitude());
      System.out.println(address.getLongitude());
      System.out.println(address.getDisplayName());
    }
  }
  
  private static void sendPost() throws Exception {

    String url = "https://www.linkedin.com/uas/login-submit";
    URL obj = new URL(url);
    HttpURLConnection con = (java.net.HttpURLConnection ) obj.openConnection();
    String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:41.0) Gecko/20100101 Firefox/41.0";
    //add reuqest header
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

    String urlParameters = "session_redirect=https://www.linkedin.com/vsearch/p?keywords=nguyen&pt=people&locationType=Y&rsid=4475293861445439150254&page_num=21&f_G=us%3A0%2Cfr%3A0%2Cau%3A0%2Cus%3A84%2Cde%3A0%2Cit%3A0%2Cca%3A0%2Cua%3A0%2Cgb%3A0%2Ccz%3A0%2Car%3A0%2Cbr%3A0%2Cjp%3A0%2Chk%3A0%2Ckr%3A0%2Csg%3A0&orig=FCTD&openAdvancedForm=true&"
        + "session_key=ing.dev.java@gmail.com&session_password=ngocanh";
    
    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + urlParameters);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    
    //print result
    System.out.println(response.toString());

  }
  
  public static void test(String a) {
    URL url;
    try {
      // get URL content

      //String a="https://fr.foursquare.com/explore?mode=url&near=Californie&q=pho";
      //String a = "http://annuairesante.ameli.fr/";
      url = new URL(a);
      URLConnection conn = url.openConnection();

      // open the stream and put it into BufferedReader
      BufferedReader br = new BufferedReader(
                         new InputStreamReader(conn.getInputStream()));

      String inputLine;
      while ((inputLine = br.readLine()) != null) {
              System.out.println(inputLine);
      }
      br.close();

      System.out.println("Done");

  } catch (MalformedURLException e) {
      e.printStackTrace();
  } catch (IOException e) {
      e.printStackTrace();
  }
  }

}
