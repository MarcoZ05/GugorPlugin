package gugor.gugor;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class verify_player implements Listener {
  public static Plugin plugin;
  public verify_player(Plugin plugin){
    verify_player.plugin = plugin;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player p = event.getPlayer();
    event.setJoinMessage(
        "<Gugor> Willkommen auf dem Server " + ChatColor.YELLOW + p.getName() + "!");

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("https://gugor-myeqf.ondigitalocean.app/api/player"))
            .header("Content-Type", "application/json")
            .GET()
            .build();

    HttpClient client = HttpClient.newHttpClient();
    JSONArray players;
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      players = new JSONArray(response.body());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

    for (int i = 0; i < players.length(); i++) {
      String uuid = players.getJSONObject(i).getString("minecraftUUID");
      if (Objects.equals(uuid, p.getUniqueId().toString())) {
        p.sendMessage("<Gugor> Mit /waypoint help stehen dir alle Türen offen!");
        return;
      }
    }
    String url = "https://gugor-myeqf.ondigitalocean.app/verify/?minecraftUsername=" + p.getName();

    new BukkitRunnable() {

      @Override
      public void run() {
        p.kickPlayer("Bitte verifiziere dich, um auf dem Server spielen zu können!");
      }

    }.runTaskLater(plugin, 200);


    TextComponent message = new TextComponent("[ Verifizeren ]");
    message.setColor(net.md_5.bungee.api.ChatColor.GREEN);
    message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
    p.sendMessage(ChatColor.RED + "Bitte verifiziere dich, um auf dem Server spielen zu können!");
    p.spigot().sendMessage(message);
  }
}
