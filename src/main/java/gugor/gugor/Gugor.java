package gugor.gugor;

import gugor.gugor.commands.ping.ping;
import gugor.gugor.commands.stats.stats;
import gugor.gugor.commands.waypoint.waypoint;
import gugor.gugor.events.player_death;
import gugor.gugor.events.player_join;
import gugor.gugor.events.player_move;
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
        Objects.requireNonNull(getCommand("ping")).setExecutor(new ping());
        Objects.requireNonNull(getCommand("ping")).setTabCompleter(new ping());
        Objects.requireNonNull(getCommand("waypoint")).setExecutor(new waypoint());
        Objects.requireNonNull(getCommand("waypoint")).setTabCompleter(new waypoint());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new stats());
        Objects.requireNonNull(getCommand("stats")).setTabCompleter(new stats());
        // init event handlers
        Bukkit.getPluginManager().registerEvents(new player_join(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new player_death(), plugin);
        Bukkit.getPluginManager().registerEvents(new player_move(), plugin);
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