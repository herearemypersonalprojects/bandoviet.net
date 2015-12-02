package net.bandoviet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Quyet dinh phan nao duoc truy cap can hoac khong can dang nhap.
 * @author quocanh
 *
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter 
                            implements ApplicationContextAware {
  
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable(); // CSRF protection
    http.authorizeRequests().antMatchers("/img/**", "/fonts/**", "/libs/**").permitAll();
    http.authorizeRequests()
              //.antMatchers("/", "/index", "/place/**", "/public/**").permitAll()
              .antMatchers("/public/**", "/place/**", "/user/create", "/trangVangCrawler/**",
                  "/active/**", "/create", "/update", "/feedback/**", "/crawYelp/**").permitAll()
              .antMatchers("/users/**").hasAuthority("SUPERADMIN")
              .anyRequest().fullyAuthenticated()
              .and()
              .formLogin()
                  .loginPage("/login")
                  .failureUrl("/login?error")
                  .usernameParameter("email")
                  .permitAll()
              .and()
              .logout()
                  .logoutUrl("/logout")
                  .deleteCookies("remember-me")
                  .logoutSuccessUrl("/")
                  .permitAll()
              .and()
              .rememberMe()
              .tokenValiditySeconds(157680000);
  }
  
  
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
              .userDetailsService(userDetailsService);
              //.passwordEncoder(new BCryptPasswordEncoder());
  }
}
