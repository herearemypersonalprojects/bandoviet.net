package net.bandoviet.place;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.bandoviet.type.Type;
import net.bandoviet.type.TypeService;
import net.bandoviet.user.CurrentUser;
import net.bandoviet.user.User;
import net.bandoviet.user.UserService;





/**
 * Controller (MVC) for places.
 * 
 * @author quocanh
 *
 */
@Controller
public class PlaceController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);
  
  private static final String PLACE_PATH = "/place/";
  private static final String PLACES_PUBLIC = "/public/";
  private static final String PLACES_CATEGORY_PATH = "/places/category/";
  private static final String PLACES_KEYWORDS_PATH = "/places/searchterms/";
  private final PlaceService placeService;
 
  @Autowired 
  private UserService userService;
  
  @Autowired
  private TypeService typeService;
   
  @Autowired
  public PlaceController(final PlaceService placeService) {
    this.placeService = placeService;
  }

  @PreAuthorize("hasAuthority('SUPERADMIN')")  //da cau hinh trong SecurityConfig
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(Map<String, Object> model, 
      @PathVariable Long id, HttpServletRequest request) {
    placeService.delete(id);
    return "redirect:/index";
  }
  /**
   * index.
   * 
   * @param model
   *          communication between frontend and backend
   *          get user's ip
   * @return list of filtered POIs
   */
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(Authentication authentication, Map<String, Object> model) {

    if (authentication != null && authentication.getPrincipal() != null) {
      CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
      Optional<User> user = userService.getUserByEmail(currentUser.getUsername());
      if (user.isPresent()) {
        StringBuffer categories = new StringBuffer(); 
        
        List<Type> lstTypes = typeService.findAll();
        for (Type type : lstTypes) {
          if (type.getNhom().equalsIgnoreCase("PUBLIC")) {
            categories.append(type.getCode() + "aaa");
          }
        }
        
        return "redirect:" + "/search/COMPANY/1";
      } 
    } 
   
    return "redirect:/login";
    
  }
  
  @RequestMapping(value = "/contribution/{pageNumber}", method = RequestMethod.GET)
  public String contribution(Map<String, Object> model, 
      @PathVariable Integer pageNumber, HttpServletRequest request) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    List<Place> items = placeService.searchByContribution(auth.getName(), pageNumber);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByContribution(auth.getName());
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", "/contribution/");
    
    return "index";
  }
  
  @RequestMapping(value = "/search/"
                        + "{categories}/{searchTerms}/{lat}/{lng}/{country}/{address:.+}/{pageNumber}",
      method = RequestMethod.GET)
  public String searchByCategories(Map<String, Object> model,
      @PathVariable String categories,
      @PathVariable String searchTerms, 
      @PathVariable Double lat, 
      @PathVariable Double lng,
      @PathVariable String country, 
      @PathVariable String address,
      @PathVariable Integer pageNumber) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    searchTerms = searchTerms.replaceAll("\"", "\'");
    String[] types = categories.split("aaa");
    List<Place> items = placeService.searchByKeywordsLocation(auth.getName(), Arrays.asList(types), pageNumber, searchTerms, lat, lng, country);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByKeywordsLocation(auth.getName(), Arrays.asList(types), searchTerms, lat, lng, country);
    int end = Math.min(begin + 10, totalPages);
    /*
    if (totalPages == 0) {
      System.out.println("Redirect");
      return "redirect:/create";
    }
    */

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", "/search/" + categories + "/" + searchTerms + "/" + lat + "/" + lng + "/" + country + "/" + address + "/");

    model.put("lat", lat);
    model.put("lng", lng);
    model.put("country", country);
    model.put("address", address);
    
    model.put("categories", categories);
    model.put("keywords", searchTerms);
    return "index";     
  }
  
  
  @RequestMapping(value = "/search/"
                    + "{categories}/{searchTerms}/{pageNumber}",
      method = RequestMethod.GET)
  public String searchByCategories(Map<String, Object> model,
      @PathVariable String categories,
      @PathVariable String searchTerms, 
      @PathVariable Integer pageNumber) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    searchTerms = searchTerms.replaceAll("\"", "\'");
    String[] types = categories.split("aaa");
    List<Place> items = placeService.searchByKeywords(auth.getName(), Arrays.asList(types), pageNumber, searchTerms);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByKeywords(auth.getName(), Arrays.asList(types), searchTerms);
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", PLACES_KEYWORDS_PATH + searchTerms + "/");
    
    model.put("keywords", searchTerms);
  
    return "index";
  }
  
  
  @RequestMapping(value = "/search/"
                    + "{categories}/{lat}/{lng}/{country}/{address:.+}/{pageNumber}",
      method = RequestMethod.GET)
  public String searchByCategories(Map<String, Object> model,
      @PathVariable String categories,
      @PathVariable Double lat, 
      @PathVariable Double lng,
      @PathVariable String country, 
      @PathVariable String address,
      @PathVariable Integer pageNumber) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    String[] types = categories.split("aaa");
    List<Place> items = placeService.searchByKeywordsLocation(auth.getName(), Arrays.asList(types), pageNumber, null, lat, lng, country);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByKeywordsLocation(auth.getName(), Arrays.asList(types), null, lat, lng, country);
    int end = Math.min(begin + 10, totalPages);
    
    /*
    if (totalPages == 0) {
      System.out.println("Redirect");
      return "redirect:/create";
    }
    */

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", "/search/" + categories + "/" + lat + "/" + lng + "/" + country + "/" + address + "/");

    model.put("lat", lat);
    model.put("lng", lng);
    model.put("country", country);
    model.put("address", address);
    
    model.put("categories", categories);
    
    
    return "index";     
  }
  
  @RequestMapping(value = "/search/{categories}/{pageNumber}", method = RequestMethod.GET)
  public String searchByCategories(Map<String, Object> model,
      @PathVariable String categories,
      @PathVariable Integer pageNumber) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    String[] types = categories.split("aaa");
    List<Place> items = placeService.searchByCategories(auth.getName(), pageNumber, types);
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByCategories(auth.getName(), types);
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("categories", categories);
    model.put("path", "/search/" + categories + "/");
  
    return "index";
  }
  
  /**
   * search by keywords and location.
   */
  @RequestMapping(value = PLACES_KEYWORDS_PATH 
                          + "{searchTerms}/{lat}/{lng}/{country}/{address:.+}/{pageNumber}", 
      method = RequestMethod.GET)
  public String searchByKeyWordsLocationPagination(Map<String, Object> model, 
      @PathVariable String searchTerms, 
      @PathVariable Double lat, 
      @PathVariable Double lng,
      @PathVariable String country, 
      @PathVariable String address,
      @PathVariable Integer pageNumber) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    searchTerms = searchTerms.replaceAll("\"", "\'");
    
    List<Place> items = 
        placeService.searchByKeywordsLocation(auth.getName(), 
            pageNumber, searchTerms, lat, lng, country);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByKeywordsLocation(auth.getName(), 
        searchTerms, lat, lng, country);
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", PLACES_KEYWORDS_PATH
                      + searchTerms + "/" + lat + "/" + lng + "/" + country + "/" + address + "/");
    
    model.put("keywords", searchTerms);
    model.put("lat", lat);
    model.put("lng", lng);
    model.put("country", country);
    model.put("address", address);
    
    return "index";
  }
  
  /**
   * search by location.
   */
  @RequestMapping(value = PLACES_KEYWORDS_PATH + "{lat}/{lng}/{country}/{address:.+}/{pageNumber}", 
      method = RequestMethod.GET)
  public String searchByLocationPagination(Map<String, Object> model, 
      @PathVariable Double lat, 
      @PathVariable Double lng,
      @PathVariable String country, 
      @PathVariable String address,
      @PathVariable Integer pageNumber) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    List<Place> items = 
        placeService.searchByKeywordsLocation(auth.getName(), pageNumber, null, lat, lng, country);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByKeywordsLocation(auth.getName(), 
        null, lat, lng, country);
    int end = Math.min(begin + 10, totalPages);
    
    /*
    if (totalPages == 0) {
      System.out.println("Redirect");
      return "redirect:/create";
    }
    */

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", PLACES_KEYWORDS_PATH + lat + "/" + lng + "/" + country + "/" + address + "/");

    model.put("lat", lat);
    model.put("lng", lng);
    model.put("country", country);
    model.put("address", address);
    
    return "index";
  }
  
  /**
   * search by keywords.
   */
  @RequestMapping(value = PLACES_KEYWORDS_PATH 
                          + "{searchTerms}/{pageNumber}", 
      method = RequestMethod.GET)
  public String searchByKeyWordsPagination(Map<String, Object> model, 
      @PathVariable String searchTerms, 
      @PathVariable Integer pageNumber) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    searchTerms = searchTerms.replaceAll("\"", "\'");
    List<Place> items = placeService.searchByKeywords(auth.getName(), pageNumber, searchTerms);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByKeywords(auth.getName(), searchTerms);
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", PLACES_KEYWORDS_PATH + searchTerms + "/");
    
    model.put("keywords", searchTerms);
  
    return "index";
  }
  /**
   * @return POIs by pagination.
   */
  @RequestMapping(value = PLACES_PUBLIC + "{pageNumber}", method = RequestMethod.GET)
  public String indexPagination(Map<String, Object> model, 
      @PathVariable Integer pageNumber, HttpServletRequest request) {

    List<Place> items = placeService.searchByPublic(pageNumber);
    
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByPublic();
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", PLACES_PUBLIC);
    
    return "index";
  }
  
  /**
   * search by category with pagination.
   */
  @RequestMapping(value = PLACES_CATEGORY_PATH + "{type}/{pageNumber}", method = RequestMethod.GET)
  public String personalPlaces(Map<String, Object> model, 
      @PathVariable String type, @PathVariable Integer pageNumber, HttpServletRequest request) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }

    List<Place> items = placeService.searchByCategory(auth.getName(), pageNumber, type);
    int current = pageNumber;
    int begin = Math.max(1, current - 5);
    int totalPages = placeService.getTotalPagesByCategory(auth.getName(), type);
    int end = Math.min(begin + 10, totalPages);

    model.put("totalPages", totalPages);
    model.put("beginIndex", begin);
    model.put("endIndex", end);
    model.put("currentIndex", current);
    model.put("items", items);
    model.put("path", PLACES_CATEGORY_PATH + type + "/");
    
    model.put("keywords", "");
    
    return "index";
  }
  
  
  /**
   * Open each POI in its propre page.
   */
  @RequestMapping(value = PLACE_PATH + "{titleWithoutAccents}/{id}", method = RequestMethod.GET)
  public String showPlace(Map<String, Object> model, @PathVariable String titleWithoutAccents,
      @PathVariable Long id) {
    Place place = placeService.getPlace(id);
    //model.put("place", place);
    //return "portfolio";
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
  public String search(@RequestParam String keywords, 
      Map<String, Object> model, HttpServletRequest request) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    
    List<Place> items = placeService.search(auth.getName(), keywords);
    //System.out.println(items.size());
    model.put("items", items);
    model.put("keywords", keywords);
    return "index";
  }
  
  /**
   * Search by category. '/category/INDIVIDUAL/0';
   * @param type given category.
   * @param model MVC communication.
   * @return list of places.
   */
  @RequestMapping(value = {"/category", "/category/"}, method = RequestMethod.GET)
  public String searchByCategory(
      @RequestParam(value = "type", required = false, defaultValue = "INDIVIDUAL") String type, 
      Map<String, Object> model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return "redirect:/login";
    }
    List<Place> items = placeService.searchByCategory(auth.getName(), type);
    //System.out.println(items.size());
    model.put("items", items);
    model.put("keywords", "");
    model.put("type", PlaceType.valueOf(type));
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
      @RequestParam(value = "placeType", required = false) String placeType,
      Map<String, Object> model) {
    LOGGER.info("Received request to create a new place");
    Place place = placeService.initNewPlace();
    if (placeType != null) {
      place.setPlaceType(placeType);
    }
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
  //@PreAuthorize("hasAuthority('USER')")
  @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
  public ModelAndView save(Map<String, Object> model, 
      @ModelAttribute("place") @Valid final Place place,       
      @RequestParam("image") MultipartFile image,
      BindingResult result, Authentication authentication,
      HttpServletRequest request ) {
    
    LOGGER.info("Received request to save {}, with result={}", place, result);
    if (result.hasErrors()) {
      initModel(place, model, "vn");
      return new ModelAndView("edit");
    }
    // luu thong tin ai la nguoi edit, add
    if (authentication != null && authentication.getPrincipal() != null) {
      CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
      place.setCreatedByUser(currentUser.getUsername());
      place.setCreatedByUserFullname(currentUser.getFullname());
    } else {
      place.setCreatedByUser(null);
    }
    place.setCreatedFromIp(request.getRemoteAddr());
    placeService.save(place, image);
    


    // RETURN THE ADDED PLACE
    return new ModelAndView("redirect:" + PLACE_PATH 
        + place.getTitleWithoutAccents() + "/" + place.getId());
    //return new ModelAndView("redirect:/");
  }
    
  /**
   * Suggestions when typing search keywords.
   * @param query keywords.
   * @return list of suggestions.
   */
  @RequestMapping(value = {"/autocomplete"}, method = RequestMethod.GET)
  @ResponseBody public  List<String> autocomplete(
      @RequestParam String query,
      @RequestParam Double lat,
      @RequestParam Double lng,
      @RequestParam String country) {
    

    
    List<String> results = new ArrayList<String>();
    
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return results;
    }
    
    List<Place> items = 
        placeService.searchByKeywordsLocation(auth.getName(), 
            new Integer(1), query, lat, lng, country);
    for (Place place : items) {
      results.add(place.getTitle() + " (" + place.getCity() + ")");
    }
    return results;
  }

} // class PlaceController
