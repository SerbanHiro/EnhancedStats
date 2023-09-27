package me.serbob.enhancedstats.TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatsTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if(args.length<2) {
            for(OfflinePlayer offlinePlayer: Bukkit.getOfflinePlayers()) {
                list.add(offlinePlayer.getName());
            }
            String letters = args[0].toLowerCase();
            list = list.stream().filter(s -> s.toLowerCase().startsWith(letters)).collect(Collectors.toList());
        }
        return list;
    }
}
