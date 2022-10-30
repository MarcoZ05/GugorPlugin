package gugor.gugor.commands.waypoint;

import gugor.gugor.api;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class waypoint implements TabExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player){
      // if sender is a player
      Player p = (Player) sender;

      switch (args[0]){
        case "help":
          help.main(p);
          break;
        case "getall":
          getall.main(p);
          break;
        case "track":
          track.main(p,args);
          break;
        case "set":
          if(args.length >= 2){
            set.main(p,args);
          }else{
            p.sendMessage("<Gugur>" + ChatColor.RED + " Dieser Befehl benötigt mindestens 2 Argumente!");
          }
          break;
        case "get":
          if(args.length >= 2){
            get.main(p, args[1]);
          }else{
            p.sendMessage("<Gugur>" + ChatColor.RED + " Dieser Befehl benötigt 2 Argumente!");
          }
          break;
        case "delete":
          if(args.length >= 2){
            try {
              delete.main(p, args[1]);
            } catch (IOException | URISyntaxException | InterruptedException e) {
              throw new RuntimeException(e);
            }
          }else{
            p.sendMessage("<Gugur>" + ChatColor.RED + " Dieser Befehl benötigt 2 Argumente!");
          }
          break;
        default:
          error.main(p, args);
          break;
        }
      }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if(args.length > 1){
      if(List.of("get", "delete", "track").contains(args[0])){
        JSONArray wps = api.get_waypoints();
        List<String> wpNames = new java.util.ArrayList<>(List.of());
        for (int i = 0; i < wps.length(); i++){
          wpNames.add(String.valueOf(wps.getJSONObject(i).getString("name")));
        }
        for (int i = 0; i < wpNames.size(); i++) {
          if(!wpNames.get(i).contains(args[1])){
            wpNames.remove(i);
          }
        }
        if(Objects.equals(args[0], "track")){
          wpNames.add("stop");
        }
        return wpNames;
      }
      if(Objects.equals(args[0], "set")){
        if(args.length == 6){
          return List.of("overworld","nether","end");
        }
        if(args.length == 5){
          return List.of("<?z>");
        }
        if(args.length == 4){
          return List.of("<?y>");
        }
        if(args.length == 3){
          return List.of("<?x>");
        }
        if(args.length == 2){
        return List.of("<name>");
        }
      }
    }
    if(args.length < 2){
      return List.of("get", "getall", "set", "help", "track", "delete");
    }
    return null;
  }
}

