package net.bandoviet.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository  
                  extends JpaRepository<Country, String> {

  @Query(value = "SELECT * FROM countries WHERE name like :name", nativeQuery = true)
  public List<Country> findCode(@Param("name") String name);
}
