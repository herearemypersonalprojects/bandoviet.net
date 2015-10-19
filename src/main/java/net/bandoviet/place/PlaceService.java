package net.bandoviet.place;

import net.bandoviet.tool.AccentRemover;
import net.bandoviet.tool.DistanceCalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
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

  private static final int PAGE_SIZE = 20;
  
  private static final double DISTANCE = 50; // km
  
  private static final double WEIGHT_TITLE = 25;
  
  @Autowired
  public PlaceService(final PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
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
    return placeRepository.findByDistanceAndKeywords(
        keywords, lat, lng, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE, country);
   /*
    List<Place> lst = null;
    if (country.length() <= 1) {
      lst =  searchByKeywords(pageNumber, keywords);
    } else {
      if (StringUtils.isEmpty(keywords)) {
        lst = placeRepository.search(PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE, country);
        
        lst = searchByArea(lat, lng, lst); 
      } else { // keywords exist
        lst = placeRepository
            .searchByKeywords(keywords, country, PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE);
        lst = searchByArea(lat, lng, lst); 
        
        if (lst.isEmpty()) { // remove keywords contraint
          lst = placeRepository.search(PAGE_SIZE, (pageNumber - 1) * PAGE_SIZE, country);
          lst = searchByArea(lat, lng, lst); 
        }      
      }
    }
    
    if (lst.isEmpty()) { 
      lst = searchByKeywords(pageNumber, ""); // remove keywords contraint
    }
    return lst;
    */
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
  
  public int getTotalPagesByKeywordsLocation(String keywords, String country) {
    if (StringUtils.isEmpty(keywords)) {
      return placeRepository.getTotalPagesLocation(PAGE_SIZE, country);
    }
    return placeRepository.getTotalPagesByKeywordsLocation(keywords, PAGE_SIZE, country);
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
   * @return place if success.
   */
  @Transactional
  public Place save(@NotNull @Valid final Place place) {
    LOGGER.debug("Creating {}", place);
      /*
      Place existing = repository.findOne(user.getId());
      if (existing != null) {
          throw new UserAlreadyExistsException(
                  String.format("There already exists a user with id=%s", user.getId()));
      }
      */
    place.setTitleWithoutAccents(AccentRemover.toUrlFriendly(place.getTitle()));
    return placeRepository.save(place);
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
