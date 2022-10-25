package gugor.gugor;

import gugor.gugor.commands.verify.verify;
import gugor.gugor.commands.waypoint.waypoint;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Gugor extends JavaPlugin {
    @Override
    public void onEnable() {
        // init commands
        Objects.requireNonNull(getCommand("waypoint")).setExecutor(new waypoint(this));
        Objects.requireNonNull(getCommand("waypoint")).setTabCompleter(new waypoint(this));
        Objects.requireNonNull(getCommand("verify")).setExecutor(new verify());
        // init event handlers
        Bukkit.getPluginManager().registerEvents(new VerifyPlayer(this), this);
        // show plugin started
        System.out.println("Gugor started");
    }

    @Override
    public void onDisable() {
        System.out.println("Gugor closed");
    }
}