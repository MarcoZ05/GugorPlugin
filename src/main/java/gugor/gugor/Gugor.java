package gugor.gugor;

import gugor.gugor.commands.waypoint.waypoint;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public final class Gugor extends JavaPlugin {
    public Plugin plugin = this;
    @Override
    public void onEnable() {
        // init commands
        Objects.requireNonNull(getCommand("waypoint")).setExecutor(new waypoint(plugin));
        Objects.requireNonNull(getCommand("waypoint")).setTabCompleter(new waypoint(plugin));
        // init event handlers
        Bukkit.getPluginManager().registerEvents(new verify_player(plugin), plugin);
        // show plugin started
        System.out.println("Gugor started");

        // update player stats
        new BukkitRunnable() {

            @Override
            public void run() {
                update_player.main(plugin);
            }

        }.runTaskTimer(plugin, 0L, 1200L);
    }

    @Override
    public void onDisable() {
        System.out.println("Gugor closed");
    }
}