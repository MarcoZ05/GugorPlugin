package gugor.gugor;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.JSONArray;

import java.util.Objects;

public class update_player {
  public static void main(Plugin plugin){
    // break if no one online
    if(plugin.getServer().getOnlinePlayers().size() == 0){
      return;
    }

    // get player Data
    JSONArray playerData = api.get_players();

    // upload all data
    for(Player player : plugin.getServer().getOnlinePlayers())
    {
      for (int i = 0; i < playerData.length(); i++) {
        if(Objects.equals(player.getUniqueId().toString(), playerData.getJSONObject(i).getString("minecraftUUID"))){
          api.put_player(player);
        }
      }
    }
  }
}
