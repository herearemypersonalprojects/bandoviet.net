package net.bandoviet.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * requests for blog services.
 * 
 * @author quocanh
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
  private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
  
  @Autowired  private BlogService blogService;
  
  @RequestMapping(method = RequestMethod.GET, value = "{id}")
  public Blog get(@PathVariable Long id) {
    LOGGER.debug("Received request to get the blog with id {}", id);
    return blogService.get(id);
  }
}
