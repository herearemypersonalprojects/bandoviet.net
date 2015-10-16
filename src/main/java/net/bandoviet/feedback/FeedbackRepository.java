package net.bandoviet.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Mysql database access via JPA.
 * @author quocanh
 *
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
