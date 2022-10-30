package gugor.gugor.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class player_death implements Listener {
  @EventHandler
  public void onDeath(PlayerDeathEvent event){
    // get player and their data
    Player player = event.getEntity();
    long x = Math.round(player.getLocation().getX());
    long y = Math.round(player.getLocation().getY());
    long z = Math.round(player.getLocation().getZ());
    // set deathMessage
    if (player.getKiller() != null) {
      event.setDeathMessage(
          "<Gugor> "
              + ChatColor.RED
              + player.getName()
              + ChatColor.WHITE
              + " wurde von "
              + player.getKiller()
              + " getötet "
              + ChatColor.GREEN
              + "["
              + x
              + ","
              + y
              + ","
              + z
              + "]");
    } else {
      event.setDeathMessage(
              "<Gugor> "
                      + ChatColor.RED
                      + player.getName()
                      + ChatColor.WHITE
                      + " ist gestorben "
                      + ChatColor.GREEN
                      + "["
                      + x
                      + ","
                      + y
                      + ","
                      + z
                      + "]");
    }
  }
}
