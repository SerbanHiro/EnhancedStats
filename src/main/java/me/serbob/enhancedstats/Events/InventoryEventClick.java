package me.serbob.enhancedstats.Events;

import me.serbob.enhancedstats.Managers.Inventory.MainHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryEventClick implements Listener {
    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item= event.getCurrentItem();
        InventoryHolder holder = event.getWhoClicked().getOpenInventory().getTopInventory().getHolder();
        if(holder instanceof MainHolder) {
            event.setCancelled(true);
        }
    }
}
