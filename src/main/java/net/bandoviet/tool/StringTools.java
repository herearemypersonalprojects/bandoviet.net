package net.bandoviet.tool;

public class StringTools {
  
  public static String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  
  /**
   * check if a string is numeric?.
   */
  public static boolean isNumeric(String str) {
    return str.matches("[-+]?\\d*\\.?\\d+");
  }
 
}
