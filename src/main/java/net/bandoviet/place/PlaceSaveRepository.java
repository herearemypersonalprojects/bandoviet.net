package net.bandoviet.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author quocanh
 *
 */
@Repository
public interface PlaceSaveRepository  extends JpaRepository<PlaceSave, Long> {

}
