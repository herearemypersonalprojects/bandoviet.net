package net.bandoviet.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configurations for static data.
 * @author quocanh
 *
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
      .addResourceHandler("/images/**/*")
      .addResourceLocations("file://" + System.getProperty("user.home") + "/images/");
  }
}
