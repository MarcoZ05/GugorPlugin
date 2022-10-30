package gugor.gugor.events;

import gugor.gugor.api;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.json.JSONObject;

import java.util.List;

public class player_move implements Listener {
  @EventHandler
  public static void main(PlayerMoveEvent event){
    Player player = event.getPlayer();
    JSONObject player_data = api.get_player(player);
    if (player_data == null) {
      event.setTo(event.getFrom());
    }
    else if (player_data.has("trackedWaypoint")) {
      JSONObject tracked_waypoint = api.get_waypoint_by_name(player_data.getString("trackedWaypoint"));
      assert tracked_waypoint != null;
      String wp_name = tracked_waypoint.getString("name");
      int x = tracked_waypoint.getJSONObject("coordinates").getInt("x");
      int y = tracked_waypoint.getJSONObject("coordinates").getInt("y");
      int z = tracked_waypoint.getJSONObject("coordinates").getInt("z");

      int p_x = (int) Math.round(player.getLocation().getX());
      int p_z = (int) Math.round(player.getLocation().getZ());

      int distance_x = Math.abs(p_x - x);
      int distance_z = Math.abs(p_z - z);
      int distance = (int) Math.round(Math.sqrt(Math.pow(distance_x,2) + Math.pow(distance_z,2)));

      String arrow = "";

      // ← ↑ → ↓ ↖ ↗ ↘ ↙ ✓

      String message = wp_name + ChatColor.GREEN + " [" + x + "," + y + "," + z + "] " + ChatColor.AQUA + arrow + " " + distance + "m";
      player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
  }
}
