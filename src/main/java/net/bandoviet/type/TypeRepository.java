package net.bandoviet.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Mysql database access via JPA.
 * @author quocanh
 *
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
