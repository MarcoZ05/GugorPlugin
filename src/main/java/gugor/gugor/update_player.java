package gugor.gugor;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class update_player {
  public static void main(Plugin plugin){
    // break if no one online
    if(plugin.getServer().getOnlinePlayers().size() == 0){
      return;
    }
    // get id's
    JSONArray getPlayerData;
    HttpRequest getRequest =
            HttpRequest.newBuilder()
                    .uri(URI.create("https://gugor-myeqf.ondigitalocean.app/api/player"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

    HttpClient getClient = HttpClient.newHttpClient();

    try {
      HttpResponse<String> response =
              getClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
      getPlayerData = new JSONArray(response.body());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    // upload all data
    for (Player p : plugin.getServer().getOnlinePlayers()) {
      String putUrl = "https://gugor-myeqf.ondigitalocean.app/api/player";

      String player_id = null;
      for (int i = 0; i < getPlayerData.length(); i++) {
        if (Objects.equals(
                getPlayerData.getJSONObject(i).getString("minecraftUUID"),
                p.getUniqueId().toString())) {
          player_id = getPlayerData.getJSONObject(i).getString("_id");
        }
      }

      JSONObject json = new JSONObject();
      json.put("id", player_id);
      JSONObject update = new JSONObject();
      JSONObject stats = new JSONObject();
      stats.put("numberOfDeaths", p.getStatistic(Statistic.DEATHS));
      stats.put("level", p.getLevel());
      JSONObject pos = new JSONObject();
      pos.put("dimension", location.getDimension(p));
      JSONObject coords = new JSONObject();
      coords.put("x", (int) p.getLocation().getX());
      coords.put("y", (int) p.getLocation().getY());
      coords.put("z", (int) p.getLocation().getZ());
      pos.put("coordinates", coords);
      stats.put("position", pos);
      update.put("stats", stats);
      json.put("update", update);

      HttpRequest request =
              HttpRequest.newBuilder()
                      .uri(URI.create(putUrl))
                      .header("Content-Type", "application/json")
                      .PUT(HttpRequest.BodyPublishers.ofString(json.toString()))
                      .build();
      HttpClient client = HttpClient.newHttpClient();
      try {
        client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Gugor has completed data update!");
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
