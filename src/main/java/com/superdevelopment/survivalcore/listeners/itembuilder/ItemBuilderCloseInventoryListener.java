package com.superdevelopment.survivalcore.listeners.itembuilder;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.items.builder.data.Selected;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ItemBuilderCloseInventoryListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onInvClose(InventoryCloseEvent e) {
        if(plugin.itemBuildingPlayers.containsKey(e.getPlayer().getUniqueId())) {
            Selected selection = plugin.itemBuildingPlayers.get(e.getPlayer().getUniqueId()).getSelection();
            if(selection.equals(Selected.NONE)) {
                plugin.itemBuildingPlayers.remove(e.getPlayer().getUniqueId());
            } else if(selection.equals(Selected.RARITY)) {
                plugin.itemBuildingPlayers.get(e.getPlayer().getUniqueId()).setSelection(Selected.NONE);
            }
        }
    }
}
