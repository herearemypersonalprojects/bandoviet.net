/**
 * 
 */
package blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author quocanh
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
}
