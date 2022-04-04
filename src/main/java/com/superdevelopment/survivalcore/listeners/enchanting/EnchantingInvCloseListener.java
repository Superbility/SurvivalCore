package com.superdevelopment.survivalcore.listeners.enchanting;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.builder.EnchantmentTable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EnchantingInvCloseListener implements Listener {
    @EventHandler
    private void onClose(InventoryCloseEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getPlayer());
        if(sPlayer != null) {
            if (sPlayer.getEnchantmentTable() != null) {
                sPlayer.closeEnchantmentTable();
                sPlayer.setEnchantmentTable(null);
            }
        }
    }
}
