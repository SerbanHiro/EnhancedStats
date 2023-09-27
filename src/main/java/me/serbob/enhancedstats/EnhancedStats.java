package me.serbob.enhancedstats;

import me.serbob.enhancedstats.Commands.StatsCommand;
import me.serbob.enhancedstats.Events.InventoryEventClick;
import me.serbob.enhancedstats.TabCompleter.StatsTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnhancedStats extends JavaPlugin {
    public static EnhancedStats instance;
    @Override
    public void onEnable() {
        instance=this;
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("stats").setTabCompleter(new StatsTabCompleter());
        getServer().getPluginManager().registerEvents(new InventoryEventClick(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
