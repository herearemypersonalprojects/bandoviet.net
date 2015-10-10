package net.bandoviet.log;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Log all controller's requests.
 * 
 * @author quocanh
 *
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception { 
    System.out.println("TEST PRE HANDLE: " + request.getRemoteAddr() + ":" + request.getRequestURI() + "?" + request.getQueryString());;
    return true; // if return false the controller's requests will be not called.
  }
}
