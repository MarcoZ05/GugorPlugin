package gugor.gugor.commands.ping;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

import static gugor.gugor.events.player_join.plugin;

public class ping implements TabExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player){
      Player p = (Player) sender;
      if(Objects.equals(args[0], "help")) {
        p.sendMessage("<Gugor> Der Befehl zeigt dir wie schnell dein Internet ist!");
      } else if(args.length == 1){
        for (Player online_player : plugin.getServer().getOnlinePlayers()) {
          if(Objects.equals(args[0], online_player.getName())){
            p.sendMessage("<Gugor> Der Ping von " + args[0] + " beträgt " + p.getPing());
            return true;
          }
          p.sendMessage("<Gugor> "+ ChatColor.RED + args[0] + ChatColor.WHITE + " ist zurzeit nicht auf dem Server.");
        }
      } else {
        p.sendMessage("<Gugor> Dein Ping beträgt " + p.getPing());
      }
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if(args.length == 1){
      List<String> tab_complete = new java.util.ArrayList<>(List.of("help"));
      for (Player player : plugin.getServer().getOnlinePlayers()) {
        if(player.getName().contains((args[0]))){
          tab_complete.add(player.getName());
        }
      }
      return tab_complete;
    }
    return null;
  }
}
