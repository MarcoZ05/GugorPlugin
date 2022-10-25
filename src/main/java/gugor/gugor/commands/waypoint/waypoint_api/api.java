package gugor.gugor.commands.waypoint.waypoint_api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;


public class api {
  static String postUrl = "https://gugor-myeqf.ondigitalocean.app/api/waypoint";
  static String getUrl = "https://gugor-myeqf.ondigitalocean.app/api/waypoint";
  static String deleteUrl = "https://gugor-myeqf.ondigitalocean.app/api/waypoint";

  public static JSONObject get_one(String name) {
    return get_one.main(getUrl, name);
  }
  public static JSONArray get_all(){
    return get_all.main(getUrl);
  }
  public static void post(String name, String x, String y, String z, String dimension) throws IOException, InterruptedException {
    post.main(postUrl, name, x, y, z, dimension);
  }
  public static void delete(String name) throws IOException, URISyntaxException {
    delete.main(deleteUrl, name);
  }
}