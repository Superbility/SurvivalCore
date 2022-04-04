package com.superdevelopment.survivalcore.listeners.inventorylisteners;

import com.superdevelopment.survivalcore.data.configs.MenuConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StatsMenuClickListener implements Listener {
    private MenuConfig menuCfg = new MenuConfig();

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals(menuCfg.getConfig().getString("Menus.Stats.Name"))) {
            e.setCancelled(true);
        }
    }
}
