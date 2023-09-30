package me.serbob.enhancedstats.Commands;

import me.serbob.clickableheads.Classes.ClickableHead;
import me.serbob.clickableheads.Managers.Core;
import me.serbob.clickableheads.Managers.Utils.TemplateManager;
import me.serbob.enhancedstats.EnhancedStats;
import me.serbob.enhancedstats.Managers.Inventory.MainHolder;
import me.serbob.enhancedstats.Utils.GlobalUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalUtil.c("&cYou are not a player!"));
            return false;
        }
        OfflinePlayer player = (OfflinePlayer) sender;
        ClickableHead clickableHead;
        if(args.length<1) {
            clickableHead = new ClickableHead(player,player.getName(), Collections.emptyList());
            String title = GlobalUtil.c(EnhancedStats.instance.getConfig().getString("title")
                            .replace("{player}",player.getName()));
            clickableHead.initializeGUI(new MainHolder(), 36,title);
            String template = EnhancedStats.instance.getConfig().getString("clickable_heads_template");

            if(TemplateManager.doesTemplateExist(template)) {
                CompletableFuture.runAsync(() -> Core.returnTemplateGUI(clickableHead,template));
            }

            if (TemplateManager.replacePlayerStatisticPlaceholder(player, "PLAY_ONE_MINUTE") != null) {
                clickableHead.openGUI(player.getPlayer());
            }
        } else if(args.length==1) {
            String targetName = args[0];
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            if(target==null) {
                sender.sendMessage(GlobalUtil.c("&cThe player is invalid!"));
                return false;
            }
            clickableHead = new ClickableHead(target,targetName,Collections.emptyList());
            String title = GlobalUtil.c(EnhancedStats.instance.getConfig().getString("title")
                    .replace("{player}",targetName));
            clickableHead.initializeGUI(new MainHolder(), 36,title);
            String template = EnhancedStats.instance.getConfig().getString("clickable_heads_template");

            if(TemplateManager.doesTemplateExist(template)) {
                CompletableFuture.runAsync(() -> Core.returnTemplateGUI(clickableHead,template));
            }

            if (TemplateManager.replacePlayerStatisticPlaceholder(target, "PLAY_ONE_MINUTE") != null) {
                clickableHead.openGUI(player.getPlayer());
            }
        }
        return true;
    }
}
