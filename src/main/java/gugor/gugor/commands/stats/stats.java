package gugor.gugor.commands.stats;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

import static gugor.gugor.events.player_join.plugin;

public class stats implements TabExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(args.length == 0){
      return true;
    }
    Player p = null;
    for (Player player : plugin.getServer().getOnlinePlayers()) {
        if(Objects.equals(args[0], player.getName())){
          p = player;
        }
    }
    if(p == null){
      return true;
    }

    if (args.length > 1) {
      switch (args[1]) {
        case "coords":
          long x = Math.round(p.getLocation().getX());
          long y = Math.round(p.getLocation().getY());
          long z = Math.round(p.getLocation().getZ());
          p.sendMessage("<Gugor> " + p.getName() + ":" + ChatColor.GREEN + " [" + x + "," + y + "," + z +"]");
          break;
        case "xp":
          p.sendMessage("<Gugor> " + p.getName() + ": " + p.getLevel() + " Level");
          break;
        case "deaths":
          p.sendMessage("<Gugor> " + p.getName() + ": " + p.getStatistic(Statistic.DEATHS) + " Tode");
          break;
        case "walked":
          p.sendMessage("<Gugor> " + p.getName() + ": " + p.getStatistic(Statistic.WALK_ONE_CM) + " cm");
          break;
        case "sprinted":
          p.sendMessage("<Gugor> " + p.getName() + ": " + p.getStatistic(Statistic.SPRINT_ONE_CM) + " cm");
          break;
        default:
          p.sendMessage("<Gugor> Ich kenne den Status " + ChatColor.RED + args[1] + ChatColor.WHITE + " leider nicht.");
          break;
      }
    } else {
      long x = Math.round(p.getLocation().getX());
      long y = Math.round(p.getLocation().getY());
      long z = Math.round(p.getLocation().getZ());

      p.sendMessage("<Gugor> " + p.getName() + ":" + ChatColor.GREEN + " [" + x + "," + y + "," + z +"]");
      p.sendMessage("<Gugor> " + p.getStatistic(Statistic.DEATHS) + " Tode");
      p.sendMessage("<Gugor> " + p.getLevel() + " Level");
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if(args.length < 2){
      List<String> player_names = new java.util.ArrayList<>(List.of());
      for (Player p : plugin.getServer().getOnlinePlayers()) {
          player_names.add(p.getName());
      }
      return player_names;
    }
    else if(args.length < 3){
      return List.of("coords", "deaths", "xp");
    }
    return null;
  }
}
