package net.bandoviet.place;

import net.bandoviet.tool.AccentRemover;
import net.bandoviet.tool.DistanceCalculator;
import net.bandoviet.tool.FileService;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Place service.
 * 
 * @author quocanh
 *
 */

@Service
@Validated
public class PlaceService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlaceService.class);
  private final PlaceRepository placeRepository;
  
  @Autowired private PlaceSaveService placeSaveService;

  private static final int PAGE_SIZE = 50;
  
  private static final double DISTANCE = 50; // km
  
  @Autowired
  public PlaceService(final PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
  }
  
  public void delete(Long id) {
    placeRepository.delete(id);
  }
  
  /**
   * Return one page which have PAGE_SIZE items.
   * @param pageNumber page number.
   * @return list of places.
   */
  public List<Place> findAll(Integer pageNumber) {
    Pageable pageSpecification = 
        new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(Sort.Direction.ASC, "title"));
    Page<Place> requestedPage = placeRepository.findAll(pageSpecification);
    return  requestedPage.getContent();
  }
  
  public List<Place> findRefs(String url) {
    return placeRepository.findByUrl(url);
  }
  
  /*
   * Tra ve mot so cac dia diem ngau nhien.
   */
  public List<Place> getRandom(int randNum) {
    return placeRepository.findRandom(randNum);
  }
  /**
   * Return one page which have PAGE_SIZE items.
   * @param pageNumber page number.
   * @return list of places.
   */
  public Page<Place> searchByPagination(Integer pageNumber) {
    PageRequest request =
        new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "createdDate");
    return placeRepository.findAll(request);
  }
  
  /**
   * @return list of places for given search terms.
   */
  public List<Place> searchByKeywords(Integer pageNumber, String keywords) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.search(PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
    }
    List<Place> lst = placeRepository
        .searchByKeywords(keywords, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
    if (lst.isEmpty()) {
      return placeRepository.search(PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
    } else {
      return lst;
    }
  }
  
  public List<Place> searchByKeywords(List<String> types, Integer pageNumber, String keywords) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.search(types, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
    }
    List<Place> lst = placeRepository
        .searchByKeywords(types, keywords, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
    if (lst.isEmpty()) {
      return placeRepository.search(types, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
    } else {
      return lst;
    }
  }
  
  /**
   * Filtred by area of 50km from a given lst.
   */
  public List<Place> searchByArea(Double lat, Double lng, List<Place> lst) {
    List<Place> lstLatLng = new ArrayList<Place>();
    for (Place place : lst) {
      if (DistanceCalculator.distance(lat, lng, place.getLatitude(), 
                                      place.getLongitude(), "K") < DISTANCE) {
        lstLatLng.add(place);
      }
    }      
    if (!lstLatLng.isEmpty()) {
      return lstLatLng;
    } else {
      return lst;
    }
  }
  /**
   * @return list of places for given search terms in certain country or area.
   */
  public List<Place> searchByKeywordsLocation(Integer pageNumber, 
                                      String keywords, 
                                      Double lat, 
                                      Double lng, 
                                      String country) {
    List<Place> places = new ArrayList<Place>();
    if (StringUtils.isEmpty(keywords)) {
      places = placeRepository
          .findByDistance(lat, lng, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE, country);
    } else {
      places = placeRepository.findByDistanceAndKeywords(
          keywords, lat, lng, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE, country, DISTANCE);      
    }
    for (Place place : places) {
      double distance = 3959 * Math.acos( Math.cos( Math.toRadians(lat) ) 
                        * Math.cos( Math.toRadians( place.getLatitude() ) ) 
                        * Math.cos( Math.toRadians( place.getLongitude() ) - Math.toRadians(lng) )
                        + Math.sin( Math.toRadians(lat) ) 
                        * Math.sin( Math.toRadians(place.getLatitude()) ) ) ;
      place.setDistance(distance * 1.609344 * 1000);
    }
    return places;
  } 
  
  
  public List<Place> searchByKeywordsLocation(List<String> types, Integer pageNumber, 
                  String keywords, 
                  Double lat, 
                  Double lng, 
                  String country) {
    List<Place> places = new ArrayList<Place>();
    if (StringUtils.isEmpty(keywords)) {
      places = placeRepository.findByDistance(types, lat, lng, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE,
          country);
    } else {
      places = placeRepository.findByDistanceAndKeywords(types, keywords, lat, lng, PAGE_SIZE,
          (pageNumber - 1) * PAGE_SIZE, country, DISTANCE);
    }
    for (Place place : places) {
      double distance = 3959
          * Math.acos(Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(place.getLatitude()))
              * Math.cos(Math.toRadians(place.getLongitude()) - Math.toRadians(lng))
              + Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(place.getLatitude())));
      place.setDistance(distance * 1.609344 * 1000);
    }
    return places;
  } 
  
  /**
   * @return total of pages for given search terms.
   */
  public int getTotalPagesByKeywords(String keywords) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.getTotalPages(PAGE_SIZE);
    }
    return placeRepository.getTotalPagesByKeywords(keywords, PAGE_SIZE);
  }
  
  public int getTotalPagesByKeywords(List<String> types, String keywords) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.getTotalPages(types, PAGE_SIZE);
    }
    return placeRepository.getTotalPagesByKeywords(types, keywords, PAGE_SIZE);
  }
  
  /**
   * 
   * @return total of pages for search in a given location.
   */
  public int getTotalPagesByKeywordsLocation(String keywords, 
      Double lat, Double lng, String country) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.getTotalPagesLocation(PAGE_SIZE, country);
    }
    return placeRepository.getTotalPagesByKeywordsLocation(keywords, lat, lng, PAGE_SIZE, country, DISTANCE);
    /* vi du nay de lan sau biet ma su dung
    List<Object[]> results = 
        placeRepository.getTotalPagesByKeywordsLocation(keywords, lat, lng, PAGE_SIZE, country, DISTANCE);
    
    for (Object[] result : results) {
      int count = ((Number) result[0]).intValue();
      System.out.println(count);
    }
    
    return ((Number) results.get(0)[0]).intValue();
    */
  }
  
  public int getTotalPagesByKeywordsLocation(List<String> types, String keywords, 
      Double lat, Double lng, String country) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.getTotalPagesLocation(types, PAGE_SIZE, country);
    }
    return placeRepository.getTotalPagesByKeywordsLocation(types, keywords, lat, lng, PAGE_SIZE, country, DISTANCE);
  }
  
  public List<Place> searchByCategory(Integer pageNumber, String[] types) {
    return placeRepository.searchByCategory(Arrays.asList(types), PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
  }
  
  public int getTotalPagesByCategory(String[] types) {
    return placeRepository.getTotalPagesByCategory(Arrays.asList(types), PAGE_SIZE);
  }
  
  public List<Place> searchByCategory(Integer pageNumber, String type) {
    return placeRepository.searchByCategory(type, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
  }

  public int getTotalPagesByCategory(String type) {
    return placeRepository.getTotalPagesByCategory(type, PAGE_SIZE);
  }
  
  /**
   * Search by keywords.
   * 
   * @param keywords given keys.
   * @return list of corresponding places.
   */
  @Transactional(readOnly = true)
  public List<Place> search(String keywords) {
    List<Place> results = null;
    try {
      results = placeRepository.findByKeywords(keywords);
    } catch (Exception e) {
      LOGGER.error("An error occurred trying to search for places: " + e.toString());
    }
    return results;
  }
  
  /**
   * Filtred by category.
   * @param type category
   * @return list of places.
   */
  @Transactional(readOnly = true)
  public List<Place> searchByCategory(String type) {
    List<Place> results = null;
    try {
      results = placeRepository.findByType(type);
    } catch (Exception e) {
      LOGGER.error("An error occurred trying to search for places: " + e.toString());
    }
    return results;   
  }
  
  public Place getPlace(Long id) {
    return placeRepository.findOne(id);
  }
  
  /**
   * Initialize creation.
   * @return empty place with some default information.
   */
  public Place initNewPlace() {
    Place place = new Place();
    place.setCommunityCode("VN");
    return place;
  }
  
  /**
   * Save or update place.
   * @param place to be saved
   */
  @Transactional
  public void save(@NotNull @Valid final Place place, MultipartFile image) {
    if (place.getId() == null) { // chi xet trong truong hop them moi
      List<Place> lst = placeRepository.findExistings(place.getTitle(), place.getAddress());
      if (!lst.isEmpty()) {
        LOGGER.debug("The place " + place.getTitle() + " id: " + place.getId() + " exists already.");
        return;
      }
    } else { // if edit then save in history
      Place placeHistory = placeRepository.findOne(place.getId());
      placeSaveService.save(placeHistory, null);
    }
    
    place.setTitleWithoutAccents(AccentRemover.toUrlFriendly(place.getTitle()));
    Place updatedPlace = placeRepository.save(place);
    
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
        placeRepository.save(updatedPlace);       
      }

    } catch (Exception e) {
      LOGGER.error("Tried to save user with id", e);
      //result.reject("home.save.error");
      //return "edit";
    }
      /*
      Place existing = repository.findOne(user.getId());
      if (existing != null) {
          throw new UserAlreadyExistsException(
                  String.format("There already exists a user with id=%s", user.getId()));
      }
      */
    

    
  }
  

  public List<Place> findByCity(String cityName) {
    return placeRepository.findByCity(cityName);
  }

  /**
   * Return 100 nearest places around the given location.
   * 
   * @param lat
   *          given latitude
   * @param lng
   *          given longitude
   * @return list of nearest places.
   */
  public List<Place> findByLocation(double lat, double lng) {
    List<Place> lst = placeRepository.findAll();

    TreeMap<Long, Double> sortedList = new TreeMap<Long, Double>();
    for (Place place : lst) {
      double distance = PlaceTools.distance(lat, lng, place.getLatitude(), place.getLongitude(),
          "K");
      sortedList.put(place.getId(), distance);
    }

    List<Place> results = new ArrayList<Place>();
    int max = sortedList.size() <= 100 ? sortedList.size() : 100;
    for (int i = 0; i < max; i++) {
      
    }
    return results;
  }
}
