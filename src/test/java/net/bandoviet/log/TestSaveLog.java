package net.bandoviet.log;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSaveLog {
  @Mock
  private LogRepository logRepository;
  
  private LogService logService;
  
  @Before
  public void setUp() throws Exception {
      logService = new LogService(logRepository);
  }
  

  
  @Test
  public void test() {
    Log log = new Log();
    final Log returnedLog = logService.saveLog(log);
    verify(logRepository, times(1)).save(log);
    assertEquals("Returned log should come from the repository", log, returnedLog);
  }
}
