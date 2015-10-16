package net.bandoviet.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bandoviet.ipinfo.IpInfo;
import net.bandoviet.ipinfo.IpInfoService;

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
      log.setSendFromIp(request.getRemoteAddr());
      log.setRequest(request.getRequestURI()
          + (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()));

      IpInfo ipInfo = IpInfoService.getIpInfoMaxMind(IpInfoService.getClientIP(request));

      if (ipInfo != null) {
        log.setCity(ipInfo.getCity());
        log.setCountry(ipInfo.getCountry_code());
        log.setLatitude(ipInfo.getLatitude());
        log.setLongitude(ipInfo.getLongitude());
      }

      logService.saveLog(log);
    } catch (Exception e) {
      LOGGER.error("Error from save log: " + e.getMessage());
    }

    return true; // if return false the controller's requests will be not called.
  }
}
