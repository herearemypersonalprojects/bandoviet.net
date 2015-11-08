package net.bandoviet.place;

import net.bandoviet.tool.AccentRemover;
import net.bandoviet.tool.FileService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author quocanh
 *
 */
@Service
public class PlaceSaveService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlaceSaveService.class);
  @Autowired PlaceSaveRepository repository;
  
  public void save(Place place, MultipartFile image) {
    PlaceSave placeSave = new PlaceSave();
    placeSave.setPlaceId(place.getId());
    placeSave.setTitle(place.getTitle());
    placeSave.setSubtitle(place.getSubtitle());
    placeSave.setTitleWithoutAccents(AccentRemover.toUrlFriendly(place.getTitle()));
    placeSave.setInformation(place.getInformation());
    placeSave.setEmail(place.getEmail());
    placeSave.setTelephone(place.getTelephone());
    placeSave.setAddress(place.getAddress());
    placeSave.setIconPath(place.getIconPath());
    placeSave.setImagePath(place.getImagePath());
    placeSave.setCountry(place.getCountry());
    placeSave.setCity(place.getCity());
    placeSave.setStreetNumber(place.getStreetNumber());
    placeSave.setRoute(place.getRoute());
    placeSave.setPostalCode(place.getPostalCode());
    placeSave.setLatitude(place.getLatitude());
    placeSave.setLongitude(place.getLongitude());
    placeSave.setCommunityCode(place.getCommunityCode());
    placeSave.setPlaceType(place.getPlaceType());
    placeSave.setReferenceUrl(place.getReferenceUrl());
    placeSave.setIdLookitUrl(place.getIdLookitUrl());
    placeSave.setCreatedByUserId(place.getCreatedByUserId());
    placeSave.setCreatedDate(place.getCreatedDate());
    placeSave.setUpdatedDate(place.getUpdatedDate());
    placeSave.setCreatedFromIp(place.getCreatedFromIp());
    placeSave.setOpenTime(place.getOpenTime());
    placeSave.setStartTime(place.getStartTime());
    placeSave.setEndTime(place.getEndTime());
    placeSave.setSize(place.getSize());  
    placeSave.setOrganisedBy(place.getOrganisedBy());
    
    PlaceSave updatedPlace = repository.save(placeSave);
    
    // Chua tam photo vao 
    try {      
      String filename = "place" + String.valueOf(System.currentTimeMillis());
      String imagePath = FileService.saveFile(image, 
          updatedPlace.getId(), filename);
      if (StringUtils.isBlank(imagePath)) {
        if (StringUtils.isNotBlank(place.getImagePath()) 
            && place.getImagePath().indexOf("http") >= 0) {
          imagePath = FileService.saveImage(place.getImagePath(),
              updatedPlace.getId(), filename); 
        } 
      }
      if (StringUtils.isNotBlank(imagePath)) {
        updatedPlace.setImagePath(imagePath);
        repository.save(updatedPlace);       
      }

    } catch (Exception e) {
      LOGGER.error("Error from trying to save place: ", e);
    }
  }
}
