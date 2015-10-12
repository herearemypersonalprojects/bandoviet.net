package net.bandoviet.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Log all controller's requests.
 * 
 * @author quocanh
 *
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception { 
    long yourmilliseconds = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
    Date resultdate = new Date(yourmilliseconds);
    System.out.println("TEST PRE HANDLE: " + sdf.format(resultdate) + ":" + request.getRemoteAddr() + ":" + request.getRequestURI() + "?" + request.getQueryString());;
    
    
    return true; // if return false the controller's requests will be not called.
  }
}
