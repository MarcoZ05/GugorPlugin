package gugor.gugor.commands.waypoint;

import gugor.gugor.commands.waypoint.waypoint_api.api;
import gugor.gugor.location;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;

public class set {
    public static void main(Player p, String[] args){
        // get the waypoint-name from args
        String name = args[1];
        // get the players position and if location is not in args, set it to players location
        Location loc = p.getLocation();
        String x = args.length < 3 ? String.valueOf(Math.round(loc.getX())) : args[2];
        String y = args.length < 4 ? String.valueOf(Math.round(loc.getY())) : args[3];
        String z = args.length < 5 ? String.valueOf(Math.round(loc.getZ())) : args[4];
        String dimension = args.length < 6 ? location.getDimension(p) : args[5];
        try {
            // post the information and message in the chat
            api.post(name,x,y,z,dimension);
            p.sendMessage("<Gugor> The waypoint " + ChatColor.YELLOW + name + ChatColor.WHITE + " got placed " + ChatColor.GREEN + "[" + x + ", " + y + ", " + z + ", " + dimension + "]");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
