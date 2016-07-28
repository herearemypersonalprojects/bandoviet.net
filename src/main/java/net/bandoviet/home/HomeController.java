/**
 * Homepage controller.
 */

package net.bandoviet.home;

import net.bandoviet.crawl.DoanhNghiepCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bandoviet.crawl.TrangVangCrawler;
import net.bandoviet.crawl.YelpCrawler;
import net.bandoviet.linkedin.LinkedInService;
import net.bandoviet.tool.AmbassadeCrawler;
import net.bandoviet.tool.AmeliCrawler;
import net.bandoviet.tool.HncityCrawler;
import net.bandoviet.tool.LinkedInCrawler;
import net.bandoviet.tool.SocieteCrawler;
import net.bandoviet.tool.TripAdvisorCrawler;

/**
 * Receive requests from homepage.
 * @author quocanh
 *
 */
@Controller
public class HomeController {

  @Value("${application.message:Hello World}")
  private String message;
  
  @Autowired LinkedInCrawler crawler;
  
  @Autowired AmeliCrawler ameli;
  
  @Autowired HncityCrawler hncity;
  
  @Autowired TripAdvisorCrawler tripAdvisorCrawler;
  
  @Autowired AmbassadeCrawler ambassadeCrawler;
  
  @Autowired SocieteCrawler societeCrawler;
  
  @Autowired LinkedInService linkedInService;
  
  @Autowired YelpCrawler crawYelp;
  
  @Autowired TrangVangCrawler trangVangCrawler;

  @Autowired
  DoanhNghiepCrawler doanhNghiepCrawler;
  
  /**
   * Homepage
   * @param model communication between view and controller.
   * @return the login page if user has not yet connected or index page otherwise.
   */
  @RequestMapping("/")
  public String home(Map<String, Object> model, HttpServletRequest request ) {
    if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
      return "redirect:/unsupported";
    } else {
      return "redirect:/index";
    }    
  }

  @RequestMapping("/doanhNghiepCrawler")
  @ResponseBody public String doanhNghiepCrawler() {
    doanhNghiepCrawler.run();
    return "ok";
  }

  @RequestMapping("/trangVangCrawler")
  @ResponseBody public String trangVangCrawler() {
    trangVangCrawler.run();
    return "ok";
  }
  
  @RequestMapping("/linkedin")
  @ResponseBody public String linkedin() {
    linkedInService.crawl();
    return "ok";
  }
  
  @RequestMapping(value = {"/crawYelp/{country}/{city}"}, method = RequestMethod.GET )
  @ResponseBody public String crawYelp(      
      @PathVariable("country") String country,
      @PathVariable("city") String city) {
    crawYelp.run(country, city);
    return "ok";
  }
  
  @RequestMapping(value = {"/crawYelp/{country}/{city}/{ip}/{port}"}, method = RequestMethod.GET )
  @ResponseBody public String crawYelp(      
      @PathVariable("country") String country,
      @PathVariable("city") String city,
      @PathVariable("ip") String ip,
      @PathVariable("port") Integer port) {
    crawYelp.run(country, city, ip, port.intValue());
    return "ok";
  }
  //run(String country, String city, String ip, int port) 
  
  @RequestMapping(value = {"/crawYelp/{country}"}, method = RequestMethod.GET )
  @ResponseBody public String crawYelp(@PathVariable("country") String country) {
    crawYelp.run(country);
    return "ok";
  }
  
  @RequestMapping(value = {"/crawYelp"}, method = RequestMethod.GET )
  @ResponseBody public String crawYelp() {
    crawYelp.run();
    return "ok";
  }
  
  @RequestMapping("/crawlSocietes")
  @ResponseBody public String crawlSocietes() {
    societeCrawler.getData();
    return "ok";
  }
  
  @RequestMapping("/crawlAmbassades")
  public @ResponseBody String crawlAmbassades() {
    ambassadeCrawler.getAmbassades();
    return "ok";
  }
  
  @RequestMapping("/crawlTripAdvisor")
  public @ResponseBody String crawlTripAdvisor() {
    tripAdvisorCrawler.getFromTripAdvisor();
    return "ok";
  }
  
  @RequestMapping("/crawlHncity")
  public @ResponseBody String crawlHncity() {
    hncity.getDataFrom360hncity();
    return "ok";
  }
  
  @RequestMapping("/crawl")
  public @ResponseBody String crawl() {
    crawler.getDataFromLinkedin();
    return "ok";
  }
  
  @RequestMapping("/crawlAmeli")
  public @ResponseBody String crawlAmeli() {
    ameli.getDataFromAmeli();
    return "ok ameli";
  }
  
  /**
   * The first request when user open the homepage.
   * @param model communication between view and controller.
   * @return the tempate view of homepage.
   */
  @RequestMapping("/welcome")
  public String welcome(Map<String, Object> model ) {
    model.put("message", this.message + "ok");
    return "welcome";
  }
  
  /**
   * The first request when user open the homepage.
   * @param model communication between view and controller.
   * @return the tempate view of homepage.
   */
  @RequestMapping("/about")
  public String about(Map<String, Object> model ) {
    model.put("message", this.message + "ok");
    return "about";
  }
  
  @RequestMapping("/unsupported")
  public String unsupported(Map<String, Object> model) {
    return "unsupported";
  }
  
  @RequestMapping("/foo")
  public String foo(Map<String, Object> model) {
    throw new RuntimeException("Foo");
  }

}
