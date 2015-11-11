package net.bandoviet.user;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

@Repository
public class UserRepository_session {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  /*
   * 
   */
  @Bean
  public SessionFactory getSessionFactory() {
    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
      throw new NullPointerException("factory is not a hibernate factory");
    }
    return entityManagerFactory.unwrap(SessionFactory.class);
  }

  /**
   * Tim user theo email.
   */
  @SuppressWarnings("unchecked")
  public User findByEmail(String email) {
    List<User> users = new ArrayList<User>();
    users = getSessionFactory().getCurrentSession().createQuery("from user where email=?")
        .setParameter(0, email).list();
    if (users.size() > 0) {
      return users.get(0);
    } else {
      return null;
    }
  }
  
}
