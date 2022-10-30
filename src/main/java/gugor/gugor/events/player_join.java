package gugor.gugor.events;

import gugor.gugor.api;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.json.JSONObject;

public class player_join implements Listener {
  public static Plugin plugin;
  public player_join(Plugin plugin){
    player_join.plugin = plugin;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    // get player
    Player player = event.getPlayer();

    // set custom join message
    event.setJoinMessage(
        "<Gugor> Willkommen auf dem Server " + ChatColor.YELLOW + player.getName() + "!");

    // return if player is already verified
    JSONObject playerData = api.get_player(player);
    if(playerData != null){
      player.sendMessage("<Gugor> Mit /waypoint help stehen dir alle Türen offen!");
      return;
    }

    // send player a verification message
    String url = "https://gugor-myeqf.ondigitalocean.app/verify/?minecraftUsername=" + player.getName();
    TextComponent message = new TextComponent("[ Verifizieren ]");
    message.setColor(net.md_5.bungee.api.ChatColor.GREEN);
    message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
    player.sendMessage(ChatColor.RED + "Bitte verifiziere dich, um auf dem Server spielen zu können!");
    player.spigot().sendMessage(message);
  }
}

