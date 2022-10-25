package gugor.gugor.commands.waypoint;

import fr.skytasul.guardianbeam.Laser;
import gugor.gugor.commands.waypoint.waypoint_api.api;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.util.Objects;

import static org.bukkit.Bukkit.getWorld;

public class track {

  static Laser laser;

  static {
    try {
      laser = new Laser.CrystalLaser(new Location(getWorld("world"), 0, 0, 0),new Location(getWorld("world"), 0, 0, 0),0,0);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(Player p, String name) throws ReflectiveOperationException {
    if(Objects.equals(name, "stop")){
      // stop tracking the waypoint
      stopTracking();
      return;
    }

    // get the waypoints from api into JSONArray
    JSONObject waypoint = api.get_one(name);
    if(waypoint == null){
      p.sendMessage("<Gugor> The waypoint " + ChatColor.RED + name + ChatColor.WHITE + " is not existing.");
      return;
    }
    // get the rest of information and send them in the chat
    laser.moveEnd(p.getLocation());
    laser.moveStart(new Location(getWorld(waypoint.getString("world")), waypoint.getInt("x"), waypoint.getInt("y"), waypoint.getInt("z")));
    //TODO: start and track until reached or stop

    // get the distance between the waypoint and the player
    int p_x = (int) p.getLocation().getX();
    int w_x = waypoint.getInt("x");
    int distance_x = Math.abs(w_x + p_x);
    int w_z = waypoint.getInt("z");
    int p_z = (int) p.getLocation().getZ();
    int distance_z = Math.abs(w_z + p_z);
    int distance_all = (int) Math.sqrt(Math.pow(distance_x,2)+Math.pow(distance_z,2));

    // give player the distance between waypoint and player
    p.sendMessage("<Gugor> You are now tracking " + ChatColor.YELLOW + name + ChatColor.GREEN +" [ " + distance_all + "m ]");
  }

  public static void stopTracking(){
    laser.stop();
  }
}
