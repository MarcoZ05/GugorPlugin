package gugor.gugor.commands.waypoint;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class help {
    public static void main(Player p){
        p.sendMessage("");
        p.sendMessage(ChatColor.GOLD + "---------------- Waypoint Commands ----------------");
        p.sendMessage("");
        p.sendMessage(ChatColor.YELLOW + "/waypoint help");
        p.sendMessage("This Command shows you all possible commands");
        p.sendMessage("");
        p.sendMessage(ChatColor.YELLOW + "/waypoint getall");
        p.sendMessage("This Command shows you all waypoint");
        p.sendMessage("");
        p.sendMessage(ChatColor.YELLOW + "/waypoint get <name>");
        p.sendMessage("This Command shows you the waypoint" + ChatColor.YELLOW + " <name>");
        p.sendMessage("");
        p.sendMessage(ChatColor.YELLOW + "/waypoint set <name> [x] [y] [z] [dimension]");
        p.sendMessage("This Command sets a new waypoint " + ChatColor.YELLOW + "<name>" + ChatColor.WHITE +  " at your position or the given Positions ");
        p.sendMessage("");
        p.sendMessage(ChatColor.YELLOW + "/waypoint delete <name>");
        p.sendMessage("This Command removes the waypoint " + ChatColor.YELLOW + "<name>");
        p.sendMessage("");
        p.sendMessage(ChatColor.YELLOW + "/waypoint track <name>|stop");
        p.sendMessage("This Command starts tracking " + ChatColor.YELLOW + "<name>" + ChatColor.WHITE +  " or stops tracking");
        p.sendMessage("");
        p.sendMessage(ChatColor.GOLD + "---------------------------------------------------");
        p.sendMessage("");
    }
}
