package net.bandoviet.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Prepare database.
 * 
 * @author quocanh
 *
 */
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
  @Query(value = "select distinct CONCAT(city, ', ', country) as city from place", 
      nativeQuery = true)
  List<String> findAllCities();

  @Query(value = "select distinct place_type from place", nativeQuery = true)
  List<String> getListPlaceTypes();

  List<Place> findByCity(@Param("city") String city);

  @Query(value = "select * from place where place_type = :type", nativeQuery = true)
  List<Place> findByType(@Param("type") String type);

  @Query(value = "select * from place where "
      + "latitude > :swLat and "
      + "latitude < :neLat and "
      + "longitude > :swLng and "
      + "longitude < :neLng", 
      nativeQuery = true)
  List<Place> findByCurrentView(@Param("swLat") Double swLat, @Param("swLng") Double swLng,
      @Param("neLat") Double neLat, @Param("neLng") Double neLng);
  
  /**
   * Create 3 full text indexes:
   * 1. ALTER TABLE place ADD FULLTEXT INDEX `title_index` (`title` ASC);
   * 2. ALTER TABLE place ADD FULLTEXT INDEX `information_index` (`information` ASC);
   * 3. ALTER TABLE place ADD FULLTEXT INDEX `search_index` (`title` ASC, `information` ASC);
   * 4. select title, match(title) against('Ngo Bao Chau') as w1, 
   *    match(information) against('Ngo Bao Chau') as w2 
   *    from place where match(title, information) against('Ngo Bao Chau') 
   *    order by (w1*10 + w2) desc;
   * @param keywords given search words
   * @return list of places.
   */
  @Query(value = "SELECT *, "
      + "MATCH(title) AGAINST(:keywords) AS rel1, "
      + "MATCH(information) AGAINST(:keywords) AS rel2 "
      + "FROM place WHERE "
      + "MATCH(title, information) AGAINST(:keywords) "
      + "ORDER BY (rel1*10)+(rel2) desc",
      nativeQuery = true)
  List<Place> findByKeywords(@Param("keywords") String keywords);
  
  
  @Query(value = "SELECT * FROM place WHERE "
      + "city=:city and "
      + "MATCH(title, information) AGAINST(:keywords)",
      nativeQuery = true)
  List<Place> findByKeywordsAndCity(@Param("keywords") String keywords, @Param("city") String city);
}
