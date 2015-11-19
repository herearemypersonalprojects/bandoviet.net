package net.bandoviet.home;


import net.bandoviet.type.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Handle access to /admin/**.
 * 
 * @author quocanh
 *
 */

public class AccessInterceptor extends HandlerInterceptorAdapter {
  
  @Autowired TypeService typeService;

  @Override
  public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final ModelAndView modelAndView) throws Exception {

    if (modelAndView != null) {
      modelAndView.getModelMap().addAttribute("types", typeService.findAll());
    }
  }
}
