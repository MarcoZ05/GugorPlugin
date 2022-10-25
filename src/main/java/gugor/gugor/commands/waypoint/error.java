package gugor.gugor.commands.waypoint;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class error {
    public static void main(Player p, String[] args){
        StringBuilder str = new StringBuilder("/waypoint");
        for (String arg : args) {
            str.append(" ").append(arg);
        }
        p.sendMessage("<Gugor> I don't know this command: " + ChatColor.RED + str);
        p.sendMessage("<Gugor> Try " + ChatColor.YELLOW + "/waypoint help");
    }
}
