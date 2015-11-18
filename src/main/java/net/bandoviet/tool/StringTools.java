package net.bandoviet.tool;

public class StringTools {
  /**
   * check if a string is numeric?.
   */
  public static boolean isNumeric(String str) {
    return str.matches("[-+]?\\d*\\.?\\d+");
  }
}
