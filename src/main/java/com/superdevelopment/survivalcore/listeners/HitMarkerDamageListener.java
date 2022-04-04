package com.superdevelopment.survivalcore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HitMarkerDamageListener implements Listener {
    @EventHandler
    private void onDamage(EntityDamageEvent e) {
        if(e.getEntity().hasMetadata("HITMARKER")) {
            e.setCancelled(true);
        }
    }
}
