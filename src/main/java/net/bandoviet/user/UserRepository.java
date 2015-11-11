package net.bandoviet.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
  /* Only one non-default method findOneByEmail is added here. 
  * Notice that I want it to return a User wrapped in JDK8 Optional, 
  * which is somewhat a new feature of Spring, and makes handling null values easier.
  */
  Optional<User> findOneByEmail(String email);
}
