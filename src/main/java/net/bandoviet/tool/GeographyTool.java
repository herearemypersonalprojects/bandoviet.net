package net.bandoviet.tool;

/**
 * Tool to work with geography data.
 * @author quocanh
 *
 */
public class GeographyTool {

  private GeographyTool() {
  }

  /**
   * Calculate the distance in Miles or Kilometers or Nautical Miles.
   * @param lat1 the first position's latitude
   * @param lon1 longitude
   * @param lat2 latitude
   * @param lon2 longitude
   * @param unit M (Miles) or K (Kilometers) or N (Nautical Miles)
   * @return the calculated distnace.
   */
  public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
    double theta = lon1 - lon2;
    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
        + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
    dist = Math.acos(dist);
    dist = rad2deg(dist);
    dist = dist * 60 * 1.1515;
    if (unit == "K") {
      dist = dist * 1.609344;
    } else if (unit == "N") {
      dist = dist * 0.8684;
    }

    return (dist);
  }
  
  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  /*::  This function converts decimal degrees to radians            :*/
  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  private static double deg2rad(double deg) {
    return (deg * Math.PI / 180.0);
  }

  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  /*::  This function converts radians to decimal degrees            :*/
  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  private static double rad2deg(double rad) {
    return (rad * 180 / Math.PI);
  }
}
