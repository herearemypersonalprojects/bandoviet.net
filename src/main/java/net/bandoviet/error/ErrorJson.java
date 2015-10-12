package net.bandoviet.error;

import java.util.Map;

public class ErrorJson {
  public Integer status;
  public String error;
  public String message;
  public String timeStamp;
  public String trace;
  /**
   * Init error message.
   * @param status error's status
   * @param errorAttributes properties
   */
  public ErrorJson(int status, Map<String, Object> errorAttributes) {
    this.status = status;
    this.error = (String) errorAttributes.get("error");
    this.message = (String) errorAttributes.get("message");
    this.timeStamp = errorAttributes.get("timestamp").toString();
    this.trace = (String) errorAttributes.get("trace");
  }
  
  public String toString() {
    return timeStamp + ": " + error + " - " + message + " - " + status + " - " + trace;
  }
}
