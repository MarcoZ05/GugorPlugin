package gugor.gugor.commands.waypoint;

import gugor.gugor.api;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URISyntaxException;

public class delete {
    public static void main(Player p, String name) throws IOException, URISyntaxException, InterruptedException {
        api.delete_waypoint_by_name(name);
        p.sendMessage("<Gugor> The Waypoint " + ChatColor.YELLOW + name + ChatColor.WHITE + " got removed.");
    }
}
