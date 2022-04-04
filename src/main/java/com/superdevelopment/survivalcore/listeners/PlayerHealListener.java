package com.superdevelopment.survivalcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class PlayerHealListener implements Listener {
    @EventHandler
    private void onHeal(EntityRegainHealthEvent e) {
        if(e.getEntity() instanceof Player) {
            e.setAmount(((Player) e.getEntity()).getMaxHealth() * 0.05);
        }
    }
}
