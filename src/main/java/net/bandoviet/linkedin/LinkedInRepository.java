package net.bandoviet.linkedin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkedInRepository  extends JpaRepository<LinkedIn, Long> {

}
