package net.bandoviet.log;

import net.bandoviet.ipinfo.IpInfo;
import net.bandoviet.ipinfo.IpInfoService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

  @Autowired
  private LogService logService;

  /**
   * controller all incoming requests.
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (request.getRequestURI().indexOf("/error") >= 0) {
      return false;
    }

    long yourmilliseconds = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
    Date resultdate = new Date(yourmilliseconds);
    // System.out.println("TEST PRE HANDLE: " + sdf.format(resultdate) + ":"
    // + request.getRemoteAddr() + ":" + request.getRequestURI() + "?" + request.getQueryString());;

    try {
      Log log = new Log();
      log.setCreatedDate(resultdate);
      
      log.setRequest(request.getRequestURI()
          + (StringUtils.isBlank(request.getQueryString()) ? "" : "?" + request.getQueryString()));

      String ip = IpInfoService.getClientIP(request);
      IpInfo ipInfo = IpInfoService.getIpInfoMaxMind(ip);
      
      
      
      if (ipInfo.getCountry_code() == null) {
        ipInfo = IpInfoService.getIpInfo(ip);
      }
      //System.out.println(ipInfo.getCountry_code());
      log.setSendFromIp(ip);
      
      if (StringUtils.isNotBlank(ipInfo.getCountry_code())) {
        log.setCity(ipInfo.getCity());
        log.setCountry(ipInfo.getCountry_code());
        log.setLatitude(ipInfo.getLatitude());
        log.setLongitude(ipInfo.getLongitude());
      } else {
        System.out.println("Khong xac dinh duoc vi tri cua IP: " + ip);
        LOGGER.error("Khong xac dinh duoc vi tri cua IP: " + ip);
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
        log.setUserId(authentication.getName());
      }
      
      logService.saveLog(log);
    } catch (Exception e) {
      LOGGER.error("Error from save log: " + e.getMessage());
    }

    return true; // if return false the controller's requests will be not called.
  }
}
