package com.superdevelopment.survivalcore.listeners.modeselection;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ModeSelectClickListener implements Listener {
    @EventHandler
    private void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Select Mode")) {
            SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getWhoClicked());

            NBTItem item = new NBTItem(e.getCurrentItem());
            if(item.hasKey("mode")) {
                if(item.getString("mode").equals("normal")) {
                    player.setAdventure(false);
                } else if(item.getString("mode").equals("adventure")) {
                    player.setAdventure(true);
                }
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                return;
            }
            e.setCancelled(true);
        }
    }
}