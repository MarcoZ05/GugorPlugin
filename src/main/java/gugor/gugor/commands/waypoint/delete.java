package gugor.gugor.commands.waypoint;

import gugor.gugor.commands.waypoint.waypoint_api.api;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URISyntaxException;

public class delete {
    public static void main(Player p, String name) throws IOException, URISyntaxException {
        api.delete(name);
        p.sendMessage("<Gugor> The Waypoint " + ChatColor.YELLOW + name + ChatColor.WHITE + " got removed.");
    }
}
