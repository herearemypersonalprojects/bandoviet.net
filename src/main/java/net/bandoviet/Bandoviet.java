/**
 * Launch the application here.
 */

package net.bandoviet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

import net.bandoviet.home.AccessInterceptor;
import net.bandoviet.log.LogInterceptor;

/*
 * 
 * @author quocanh
 *
 */
//@SpringBootApplication // equal to @Configuration @EnableAutoConfiguration @ComponentScan
@Configuration
@ComponentScan("net.bandoviet")
@EnableAutoConfiguration
@EnableJpaRepositories
public class Bandoviet extends WebMvcConfigurerAdapter  {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Bandoviet.class, args);
  }
  
  /**
   * LoggerInterceptor to handle all controller's requests, 
   * and my AccessInterceptor to handle only PrivateController's requests.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // to hand international languages.
    registry.addInterceptor(localeChangeInterceptor());
    
    // to log all controller's requests.
    registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    
    // to handle only admin space.
    registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/admin/**");
    
    // continue by default.
    super.addInterceptors(registry);

  }
  /**
   * Implementation of LocaleResolver that uses a locale attribute in the user’s session 
   * in case of a custom setting, with a fallback to the specified default locale or the 
   * request’s accept-header locale.
   * @return the Vietnamese locale is set by default.
   */
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(Locale.forLanguageTag("VN"));
    return slr;
  }

  /**
   * this interceptor will look for a request parameter named ‘lang’ 
   * and will use its value to determine which locale to switch to.
   * For exameple: http://localhost:8080/?lang=vn
   * @return the selected locale.
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }
}
