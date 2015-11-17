package net.bandoviet.tool;

import net.bandoviet.ipinfo.IpInfo;
import net.bandoviet.ipinfo.IpInfoService;


import org.junit.Before;
import org.junit.Test;


public class TestMaxMind {

  @Before
  public void setUp() throws Exception {
   
  }

  @Test
  public void testIpInfo() {
    IpInfo ipInfo = IpInfoService.getIpInfo("83.200.56.35");
    System.out.println(ipInfo.getCity() 
        + " - " + ipInfo.getCountry_code() + " - " + ipInfo.getCountry_name() );
  }

  //@Test
  public void testMaxMind() {
    IpInfo ipInfo = IpInfoService.getIpInfoMaxMind("83.200.56.35");
    System.out.println(ipInfo.getCity() 
        + " - " + ipInfo.getCountry_code() + " - " + ipInfo.getCountry_name() );
  }

}
