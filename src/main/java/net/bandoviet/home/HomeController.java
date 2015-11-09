/**
 * Homepage controller.
 */

package net.bandoviet.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
  
  
  /**
   * Homepage
   * @param model communication between view and controller.
   * @return the login page if user has not yet connected or index page otherwise.
   */
  @RequestMapping("/")
  public String home(Map<String, Object> model ) {
    
    return "redirect:/index";
  }
  
  @RequestMapping("/linkedin")
  @ResponseBody public String linkedin() {
    linkedInService.crawl();
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
