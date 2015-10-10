package net.bandoviet.place;

import net.bandoviet.tool.FileService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



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
    //System.out.println(items.size());
    model.put("items", items);
    model.put("keywords", "Paris");
    return "index";
  }

  /**
   * Open each POI in its propre page.
   * @param model
   * @param id
   * @return
   */
  @RequestMapping("/place")
  public String index(Map<String, Object> model, @RequestParam Long id) {
    Place place = placeService.getPlace(id);
    List<Place> items = new ArrayList<Place>();
    items.add(place);
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
    //System.out.println(items.size());
    model.put("items", items);
    model.put("keywords", keywords);
    return "index";
  }
  
  /**
   * Search by category.
   * @param type given category.
   * @param model MVC communication.
   * @return list of places.
   */
  @RequestMapping(value = {"/category", "/category/"}, method = RequestMethod.GET)
  public String searchByCategory(@RequestParam String type, Map<String, Object> model) {
    List<Place> items = placeService.searchByCategory(type);
    //System.out.println(items.size());
    model.put("items", items);
    model.put("keywords", type);
    return "index";
  }
  
  /**
   * Update the item.
   * @param id item's id
   * @param lang language
   * @return fomule to update
   */
  @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
  public String update(@RequestParam Long id, 
      @RequestParam(value = "vn", required = false) String lang, 
      Map<String, Object> model) {
    LOGGER.info("Received request to update the place whose id=" + id);
    Place place = placeService.getPlace(id);
    if (!StringUtils.isBlank(lang)) {
      model.put("lang", lang);
    }
    model.put("place", place);    
    model.put("typeList", PlaceType.values());
    return "edit";
  }
  
  /**
   * Request to create a new place.
   * @param lang language
   * @param model parameters communication
   * @return form to be filled out
   */
  @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
  public String create(@RequestParam(value = "vn", required = false) String lang,
      Map<String, Object> model) {
    LOGGER.info("Received request to create a new place");
    Place place = placeService.initNewPlace();
    initModel(place, model, lang);
    return "edit";
  }
  
  private void initModel(Place place, Map<String, Object> model, String lang) {
    if (!StringUtils.isBlank(lang)) {
      model.put("lang", lang);
    }
    model.put("place", place);
    model.put("typeList", PlaceType.values());    
  }
  /**
   * Save edited place.
   * @param model params commmunication
   * @param place to be saved
   * @param image image to be saved
   * @param request get user's info
   * @return the saved place
   */
  @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
  public String save(Map<String, Object> model, 
      @ModelAttribute("place") @Valid final Place place,       
      @RequestParam("image") MultipartFile image,
      BindingResult result,
      HttpServletRequest request ) {
    LOGGER.info("Received request to save {}, with result={}", place, result);
    if (result.hasErrors()) {
      initModel(place, model, "vn");
      return "edit";
    }
 
    place.setCreatedFromIp(request.getRemoteAddr());
    Place updatedPlace = placeService.save(place);
    
    try {      
      String imagePath = FileService.saveFile(image, updatedPlace.getId(), "place");
      if (StringUtils.isBlank(imagePath)) {
        if (StringUtils.isNotBlank(place.getImagePath()) 
            && place.getImagePath().indexOf("http") >= 0) {
          imagePath = FileService.saveImage(place.getImagePath(),
              updatedPlace.getId(), "place"); 
        } else if (StringUtils.isBlank(place.getImagePath())) {
          imagePath = FileService.saveImageFromGoogleStreetView(
              updatedPlace.getLatitude(), place.getLongitude(),  
              updatedPlace.getId(), "place");        
        }
      }
      if (StringUtils.isNotBlank(imagePath)) {
        updatedPlace.setImagePath(imagePath);
        updatedPlace.setIconPath(imagePath.substring(0, imagePath.lastIndexOf("/") + 1)
            .concat("icon.jpg"));
        placeService.save(updatedPlace);       
      }

    } catch (Exception e) {
      LOGGER.error("Tried to save user with id", e);
      //result.reject("home.save.error");
      //return "edit";
    }

    // RETURN THE ADDED PLACE
    return "redirect:/place?id=" + place.getId();
  }
    
  /**
   * Suggestions when typing search keywords.
   * @param query keywords.
   * @return list of suggestions.
   */
  @RequestMapping(value = {"/autocomplete"}, method = RequestMethod.GET)
  @ResponseBody public  List<String> autocomplete(@RequestParam String query) {
    //LOGGER.info(query);
    List<String> results = new ArrayList<String>();
    List<Place> items = placeService.search(query);
    for (Place place : items) {
      results.add(place.getTitle() + ": " + place.getAddress());
    }
    return results;
  }

} // class PlaceController
