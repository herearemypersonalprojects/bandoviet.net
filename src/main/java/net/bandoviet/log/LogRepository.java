package net.bandoviet.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Prepare database.
 * 
 * @author quocanh
 *
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
