package gugor.gugor.commands.waypoint;

import gugor.gugor.api;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.JSONObject;


public class get {
    public static void main(Player p, String name){
        // init wpExists
        // get the waypoints from api into JSONArray
        JSONObject waypoint = api.get_waypoint_by_name(name);
        if(waypoint == null){
            p.sendMessage("<Gugor> The waypoint " + ChatColor.RED + name + ChatColor.WHITE + " is not existing.");
            return;
        }
        // get the rest of information and send them in the chat
        String dimension = waypoint.getString("dimension");
        JSONObject coords = waypoint.getJSONObject("coordinates");
        int x = coords.getInt("x");
        int y = coords.getInt("y");
        int z = coords.getInt("z");

        p.sendMessage("<Gugor> " + name + " at " + ChatColor.GREEN + "[" + x + ", " + y + ", " + z + ", " + dimension + "]");
    }
}
