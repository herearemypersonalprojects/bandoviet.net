package blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

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
  private final BlogService blogService;

  @Inject
  public BlogController(final BlogService blogService) {
    this.blogService = blogService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "{id}")
  public Blog get(@PathVariable Long id) {
    LOGGER.debug("Received request to get the blog with id {}", id);
    return blogService.get(id);
  }
}
