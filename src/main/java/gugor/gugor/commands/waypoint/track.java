package gugor.gugor.commands.waypoint;

import gugor.gugor.commands.waypoint.waypoint_api.api;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.json.JSONObject;

import java.util.Objects;
import static gugor.gugor.commands.waypoint.waypoint.plugin;

public class track {
  public static BukkitTask runnable = new BukkitRunnable() {
    @Override
    public void run() {}
  }.runTaskTimer(plugin, 0L, 100L);

  public static void main(Player p, String name) throws ReflectiveOperationException {
    stopTracking();

    if(Objects.equals(name, "stop")){
      // stop tracking the waypoint
      return;
    }
    // get the waypoints from api into JSONArray
    JSONObject waypoint = api.get_one(name);
    if(waypoint == null){
      p.sendMessage("<Gugor> The waypoint " + ChatColor.RED + name + ChatColor.WHITE + " is not existing.");
      return;
    }
    // get the distance between the waypoint and the player
    int p_x = (int) p.getLocation().getX();
    int w_x = waypoint.getJSONObject("coordinates").getInt("x");
    int distance_x = Math.abs(w_x - p_x);
    int w_z = waypoint.getJSONObject("coordinates").getInt("z");
    int p_z = (int) p.getLocation().getZ();
    int distance_z = Math.abs(w_z - p_z);
    int distance_all = (int) Math.sqrt(Math.pow(distance_x,2)+Math.pow(distance_z,2));

    int w_y = waypoint.getJSONObject("coordinates").getInt("y");

    // visual waypoint
    runnable = new BukkitRunnable() {
      @Override
      public void run() {
        p.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, new Location(plugin.getServer().getWorld("world"), w_x, w_y, w_z), 10, 0, 0,0, 0);
      }
    }.runTaskTimer(plugin, 0L, 100L);

    // give player the distance between waypoint and player
    p.sendMessage("<Gugor> You are now tracking " + ChatColor.YELLOW + name + ChatColor.GREEN +" [ " + distance_all + "m ]");
  }

  public static void stopTracking() {
    runnable.cancel();
  }
}
