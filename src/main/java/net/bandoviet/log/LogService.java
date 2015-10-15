package net.bandoviet.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Place service.
 * 
 * @author quocanh
 *
 */

@Service
@Validated
public class LogService {
  
  private final LogRepository logRepository;
  
  @Autowired
  public LogService(final LogRepository logRepository) {
    this.logRepository = logRepository;
  }
  
  @Transactional
  public Log saveLog(@NotNull @Valid final Log log) {
    return logRepository.save(log);
  }
}
