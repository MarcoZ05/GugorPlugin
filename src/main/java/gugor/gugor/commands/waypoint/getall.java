package gugor.gugor.commands.waypoint;

import gugor.gugor.commands.waypoint.waypoint_api.api;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

public class getall {
    public static void main(Player p, String[] args){
        // get all waypoints from api as JSONArray
        JSONArray arr = api.get_all();
        // print all the waypoints
        for (int i = 0; i < arr.length(); i++){
            JSONObject obj = arr.getJSONObject(i);
            String name = obj.getString("name");
            String dimension = obj.getString("dimension");
            JSONObject coords = obj.getJSONObject("coordinates");
            int x = coords.getInt("x");
            int y = coords.getInt("y");
            int z = coords.getInt("z");
            p.sendMessage("<Gugor> " + name + " at " + ChatColor.GREEN + "[" + String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z) + ", " + dimension + "]");
        }

        System.out.println("GUGOR DID IT");
    }
}
