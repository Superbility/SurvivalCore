package com.superdevelopment.survivalcore.listeners.anvil;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class AnvilInvCloseListener implements Listener {
    @EventHandler
    private void onClose(InventoryCloseEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getPlayer());
        if(sPlayer != null) {
            if (sPlayer.getAnvil() != null) {
                sPlayer.closeAnvil();
                sPlayer.setAnvil(null);
            }
        }
    }
}
