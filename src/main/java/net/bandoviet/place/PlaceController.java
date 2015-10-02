package net.bandoviet.place;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Controller (MVC) for places.
 * 
 * @author quocanh
 *
 */
@Controller
public class PlaceController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);

  private final PlaceService placeService;

  @Autowired
  public PlaceController(final PlaceService placeService) {
    this.placeService = placeService;
  }

  /**
   * index.
   * 
   * @param model
   *          communication between frontend and backend
   * @param request
   *          get user's ip
   * @return list of filtered POIs
   */
  @RequestMapping("/")
  public String index(Map<String, Object> model, HttpServletRequest request) {
    List<Place> items = placeService.findByCity("Paris");
    System.out.println(items.size());
    model.put("items", items);
    return "index";
  }

  /**
   * Show search results for the given query.
   *
   * @param keywords
   *          The search query.
   */
  @RequestMapping(value = { "/search/", "/search" }, method = RequestMethod.GET)
  public String index(@RequestParam String keywords, 
      Map<String, Object> model, HttpServletRequest request) {
    List<Place> items = placeService.search(keywords);
    System.out.println(items.size());
    model.put("items", items);
    model.put("keywords", keywords);
    return "index";
  }
  
  /**
   * Suggestions when typing search keywords.
   * @param query keywords.
   * @return list of suggestions.
   */
  @RequestMapping(value = {"/autocomplete"}, method = RequestMethod.GET)
  @ResponseBody public  List<String> autocomplete(@RequestParam String query) {
    LOGGER.info(query);
    List<String> results = new ArrayList<String>();
    List<Place> items = placeService.search(query);
    for (Place place : items) {
      results.add(place.getTitle() + ": " + place.getAddress());
    }
    return results;
  }

} // class PlaceController
