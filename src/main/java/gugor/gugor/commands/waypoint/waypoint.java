package gugor.gugor.commands.waypoint;

import gugor.gugor.commands.waypoint.waypoint_api.api;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class waypoint implements TabExecutor {
  public static Plugin plugin;
  public waypoint(Plugin plugin){
    waypoint.plugin = plugin;
  }
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player){
      // if sender is a player
      Player p = (Player) sender;

      if(args.length == 0 || Objects.equals(args[0], "help")){
        // help statement if no argument or help argument
        help.main(p);
      } else if(Objects.equals(args[0], "set") && args.length >= 2){
        // set a waypoint
        set.main(p,args);
      } else if (Objects.equals(args[0], "get") && args.length >= 2) {
        // waypoint get <name>
        get.main(p, args[1]);
      } else if(Objects.equals(args[0],"delete") && args.length >= 2){
        // waypoint delete <name>
        try {
          delete.main(p, args[1]);
        } catch (IOException | URISyntaxException e) {
          throw new RuntimeException(e);
        }
      }  else if(Objects.equals(args[0],"getall")) {
        // waypoint getall
        getall.main(p, args);
      } else if(Objects.equals(args[0],"track") && args.length >= 2) {
        // waypoint getall
        try {
          track.main(p, args[1]);
        } catch (ReflectiveOperationException e) {
          throw new RuntimeException(e);
        }
      } else {
        error.main(p, args);
      }
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if(args.length > 1){
      if(List.of("get", "delete", "track").contains(args[0])){
        JSONArray wps = api.get_all();
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
          return List.of("<?dimension>");
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
        return List.of("<name>");
      }
    }
    List<String> argsList = new java.util.ArrayList<>(List.of("get", "getall", "set", "help", "track", "delete"));
    for (int i = 0; i < argsList.size(); i++) {
      if(!argsList.get(i).contains(args[0])){
        argsList.remove(i);
      }
    }
    return argsList;
  }
}

