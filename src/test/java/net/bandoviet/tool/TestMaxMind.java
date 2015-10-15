package net.bandoviet.tool;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

import net.bandoviet.ipinfo.IpInfo;
import net.bandoviet.ipinfo.IpInfoService;

public class TestMaxMind {

  private IpInfoService ipService;

  @Before
  public void setUp() throws Exception {
    ipService = new IpInfoService();
  }

  @Test
  public void testIpInfo() {
    IpInfo ipInfo = ipService.getIpInfo("86.198.34.109");
    System.out.println(ipInfo.getCity() + " - " + ipInfo.getCountry_code() + " - " + ipInfo.getCountry_name() );
  }

  @Test
  public void test() throws IOException, GeoIp2Exception {
    // A File object pointing to your GeoIP2 or GeoLite2 database
    File database = new File(System.getProperty("user.home")
        + "/Documents/ws/bandoviet/src/main/webapp/libs/maxmind/GeoLite2-City.mmdb");

    // This creates the DatabaseReader object, which should be reused across
    // lookups.
    DatabaseReader reader = new DatabaseReader.Builder(database).build();

    InetAddress ipAddress = InetAddress.getByName("86.198.34.109");

    // Replace "city" with the appropriate method for your database, e.g.,
    // "country".
    CityResponse response = reader.city(ipAddress);

    Country country = response.getCountry();
    System.out.println(country.getIsoCode()); // 'US'
    System.out.println(country.getName()); // 'United States'
    System.out.println(country.getNames().get("zh-CN")); // '美国'

    Subdivision subdivision = response.getMostSpecificSubdivision();
    System.out.println("region: " + subdivision.getName()); // 'Minnesota'
    System.out.println(subdivision.getIsoCode()); // 'MN'

    City city = response.getCity();
    System.out.println("city:" + city.getName()); // 'Minneapolis'

    Postal postal = response.getPostal();
    System.out.println(postal.getCode()); // '55455'

    Location location = response.getLocation();
    System.out.println(location.getLatitude()); // 44.9733
    System.out.println(location.getLongitude()); // -93.2323

  }

}
