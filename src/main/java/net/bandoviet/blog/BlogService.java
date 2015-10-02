package net.bandoviet.blog;

import org.springframework.stereotype.Service;

/**
 * services for blog.
 * @author quocanh
 *
 */
@Service
public class BlogService {
  Blog get(Long id) {
    return new Blog();
  }
}
