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
  
  @Query(value = "SELECT * FROM place "
      + "WHERE country <> 'VN' "
      + "ORDER BY RAND() LIMIT :randNum", nativeQuery = true)
  List<Place> findRandom(@Param("randNum") int randNum);

  @Query(value = "SELECT * FROM place "
      + "WHERE country = :country "
      + "ORDER BY RAND() LIMIT :randNum", nativeQuery = true)
  List<Place> findRandom(@Param("randNum") int randNum, @Param("country") String country);
  
  @Query(value = "select * from place where reference_url like :url", nativeQuery = true)
  List<Place> findByUrl(@Param("url") String url);
  
  @Query(value = "select * from place where title like :title and address like :address",
      nativeQuery = true)
  List<Place> findExistings(@Param("title") String title, @Param("address") String address);
  
  @Query(value = "select distinct CONCAT(city, ', ', country) as city from place", 
      nativeQuery = true)
  List<String> findAllCities();

  @Query(value = "select distinct place_type from place", nativeQuery = true)
  List<String> getListPlaceTypes();

  List<Place> findByCity(@Param("city") String city);

  @Query(value = "select * from place where place_type = :type "
      + "order by updated_date desc", nativeQuery = true)
  List<Place> findByType(@Param("type") String type);

  @Query(value = "select * from place where "
      + "latitude > :swLat and "
      + "latitude < :neLat and "
      + "longitude > :swLng and "
      + "longitude < :neLng "
      + "order by updated_date desc", 
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
      + "MATCH(title, information) AGAINST(:keywords)  "
      + "ORDER BY (rel1*1000)+(rel2) desc",
      nativeQuery = true)
  List<Place> findByKeywords(@Param("keywords") String keywords);
  
  
  @Query(value = "SELECT *, "
      + "MATCH(title) AGAINST(:keywords) AS rel1, "
      + "MATCH(information) AGAINST(:keywords) AS rel2 "
      + "FROM place "
      + "WHERE "
      + "MATCH(title, information) AGAINST(:keywords) "
      + "ORDER BY (rel1*1000)+(rel2) desc "
      + "LIMIT :pageLimit OFFSET :pageOffset", 
      nativeQuery = true)
  List<Place> searchByKeywords(@Param("keywords") String type, 
                            @Param("pageLimit") Integer pageLimit, 
                            @Param("pageOffset") Integer pageOffset);
  
  @Query(value = "SELECT * "
      //+ ",MATCH(title) AGAINST(:keywords) AS rel1, "
      //+ "MATCH(information) AGAINST(:keywords) AS rel2 "
      + "FROM place WHERE country like :country and "
      + "MATCH(title) AGAINST(:keywords) "
      //+ "ORDER BY (rel1*1000)+(rel2) desc "
      + "LIMIT :pageLimit OFFSET :pageOffset", 
      nativeQuery = true)
  List<Place> searchByKeywords(@Param("keywords") String type, 
                            @Param("country") String country,
                            @Param("pageLimit") Integer pageLimit, 
                            @Param("pageOffset") Integer pageOffset);
  
  @Query(value = "SELECT * "
      + "FROM place order by updated_date desc "
      + "LIMIT :pageLimit OFFSET :pageOffset", 
      nativeQuery = true)
  List<Place> search(@Param("pageLimit") Integer pageLimit, 
                     @Param("pageOffset") Integer pageOffset);
  
  @Query(value = "SELECT * "
      + "FROM place "
      + "WHERE country like :country  "
      + "order by updated_date desc "
      + "LIMIT :pageLimit OFFSET :pageOffset", 
      nativeQuery = true)
  List<Place> search(@Param("pageLimit") Integer pageLimit, 
                     @Param("pageOffset") Integer pageOffset,
                     @Param("country") String country);
  
  @Query(value = "SELECT ceil(count(*)/:pageLimit) "
      + "FROM place WHERE "
      + "MATCH(title) AGAINST(:keywords)",
      nativeQuery = true)
  int getTotalPagesByKeywords(@Param("keywords") String keywords, 
      @Param("pageLimit") Integer pageLimit);
  
  @Query(value = "SELECT ceil(count(*)/:pageLimit) "
      + "FROM place WHERE country like :country  and "
      + "MATCH(title) AGAINST(:keywords) and "
      + "( 3959 * acos( cos( radians(:latitude ) ) * cos( radians( latitude ) ) "
      + "* cos( radians( longitude ) - radians(:longitude ) ) + "
      + "sin( radians(:latitude ) ) * sin( radians( latitude ) ) ) ) < :distance",
      nativeQuery = true)
  int getTotalPagesByKeywordsLocation(@Param("keywords") String keywords, 
      @Param("latitude") Double latitude,
      @Param("longitude") Double longitude,      
      @Param("pageLimit") Integer pageLimit,
      @Param("country") String country,
      @Param("distance") Double distance);
  
  @Query(value = "SELECT ceil(count(*)/:pageLimit) FROM place", nativeQuery = true)
  int getTotalPages(@Param("pageLimit") Integer pageLimit);
  
  @Query(value = "SELECT ceil(count(*)/:pageLimit) FROM place "
      + "WHERE country like :country ", nativeQuery = true)
  int getTotalPagesLocation(@Param("pageLimit") Integer pageLimit, 
      @Param("country") String country);
  
  @Query(value = "SELECT * FROM place WHERE "
      + "city=:city and "
      + "MATCH(title, information) AGAINST(:keywords) "
      + "order by updated_date desc",
      nativeQuery = true)
  List<Place> findByKeywordsAndCity(@Param("keywords") String keywords, @Param("city") String city);
  
  @Query(value = "select * from place "
      + "where place_type = :type "
      + "order by updated_date desc "
      + "LIMIT :pageLimit OFFSET :pageOffset ", 
      nativeQuery = true)
  List<Place> searchByCategory(@Param("type") String type, 
                                 @Param("pageLimit") Integer pageLimit, 
                                 @Param("pageOffset") Integer pageOffset);
  
  @Query(value = "select ceil(count(*)/:pageLimit) from place where place_type = :type", 
      nativeQuery = true)
  int getTotalPagesByCategory(@Param("type") String type, @Param("pageLimit") Integer pageLimit);
  
  /* search by distance in miles and keywords */
  @Query(value = "SELECT *, "
      + "( 3959 * acos( cos( radians(:latitude ) ) * cos( radians( latitude ) ) "
      + "* cos( radians( longitude ) - radians(:longitude ) ) + "
      + "sin( radians(:latitude ) ) * sin( radians( latitude ) ) ) ) AS distance "
      //+ ",MATCH(title) AGAINST(:keywords) AS rel1, "
      //+ "MATCH(information) AGAINST(:keywords) AS rel2 "
      + "FROM place WHERE country like :country and "
//      + "MBRContains(envelope(linestring("
//      + "point((:longitude+(:max_in_km /111)), (:latitude+(:max_in_km /111))), "
//      + "point((:longitude-(:max_in_km /111)), (:latitude-(:max_in_km /111))))), geom) AND "
      + "MATCH(title) AGAINST(:keywords) "
      + "HAVING distance < :distance "
      //+ "ORDER BY (rel1*1000)+(rel2) desc "
      + "LIMIT :pageLimit OFFSET :pageOffset ",
      nativeQuery = true)
  List<Place> findByDistanceAndKeywords(@Param("keywords") String keywords,
      @Param("latitude") Double latitude,
      @Param("longitude") Double longitude,
//      @Param("max_in_km") Double max_in_km,
      @Param("pageLimit") Integer pageLimit, 
      @Param("pageOffset") Integer pageOffset,
      @Param("country") String country,
      @Param("distance") Double distance);
  
  /* search by distance in km */
  @Query(value = "SELECT *, "
      + "( 3959 * acos( cos( radians(:latitude ) ) * cos( radians( latitude ) ) "
      + "* cos( radians( longitude ) - radians(:longitude ) ) + "
      + "sin( radians(:latitude ) ) * sin( radians( latitude ) ) ) ) AS distance "
      + "FROM place WHERE country like :country  "
      + "ORDER BY distance asc "
      + "LIMIT :pageLimit OFFSET :pageOffset",
      nativeQuery = true)
  List<Place> findByDistance(
      @Param("latitude") Double latitude,
      @Param("longitude") Double longitude,
//      @Param("max_in_km") Double max_in_km,
      @Param("pageLimit") Integer pageLimit, 
      @Param("pageOffset") Integer pageOffset,
      @Param("country") String country);
}


//SELECT ( 3959 * acos( cos( radians(48.856614 ) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(2.3522219000000177 ) ) + sin( radians(48.856614 ) ) * sin( radians( latitude ) ) ) ) AS distance FROM place WHERE country like 'FR' and distance < 50000 and MATCH(title) AGAINST('Viet nam') LIMIT 10 OFFSET 1;