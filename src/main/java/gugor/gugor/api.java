package gugor.gugor;

import gugor.gugor.api_methods.delete;
import gugor.gugor.api_methods.get;
import gugor.gugor.api_methods.post;
import gugor.gugor.api_methods.put;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Objects;


public class api {
  static String waypoint_url = "https://gugor-myeqf.ondigitalocean.app/api/waypoint/";
  static String player_url = "https://gugor-myeqf.ondigitalocean.app/api/player/";

  public static JSONArray get_waypoints(){
    return get.main(waypoint_url);
  }
  public static JSONObject get_waypoint_by_name(String name) {
    JSONArray waypoints = get.main(waypoint_url);
    for (int i = 0; i < waypoints.length(); i++) {
      if(Objects.equals(waypoints.getJSONObject(i).getString("name"), name)){
        return waypoints.getJSONObject(i);
      }
    }
    return null;
  }
  public static void post_waypoint(String name, String x, String y, String z, String dimension) throws IOException {
    String jsonBody = "{\"name\":\""+name+"\",\"coordinates\":{\"x\":"+x+",\"y\":"+y+",\"z\":"+z+"},\"dimension\":\""+dimension+"\"}";
    post.main(waypoint_url, jsonBody);
  }
  public static void delete_waypoint_by_name(String name) throws IOException {
    String waypoint_id = Objects.requireNonNull(api.get_waypoint_by_name(name)).getString("_id");
    delete.main(waypoint_url + waypoint_id);
  }

  public static JSONArray get_players(){
    return get.main(player_url);
  }
  public static JSONObject get_player(Player player) {
    JSONArray players = get.main(player_url);
    for (int i = 0; i < players.length(); i++) {
      if(Objects.equals(players.getJSONObject(i).getString("minecraftUUID"), player.getUniqueId().toString())){
        return players.getJSONObject(i);
      }
    }
    return null;
  }

  public static void put_player(Player player){
    String player_id = Objects.requireNonNull(api.get_player(player)).getString("_id");

    JSONObject jsonBody = new JSONObject();
    jsonBody.put("id", player_id);
    JSONObject update = new JSONObject();
    JSONObject stats = new JSONObject();
    stats.put("numberOfDeaths", player.getStatistic(Statistic.DEATHS));
    stats.put("level", player.getLevel());
    JSONObject pos = new JSONObject();
    pos.put("dimension", location.getDimension(player));
    JSONObject coords = new JSONObject();
    coords.put("x", (int) player.getLocation().getX());
    coords.put("y", (int) player.getLocation().getY());
    coords.put("z", (int) player.getLocation().getZ());
    pos.put("coordinates", coords);
    stats.put("position", pos);
    update.put("stats", stats);
    jsonBody.put("update", update);

    put.main(player_url, jsonBody.toString());
  }
}