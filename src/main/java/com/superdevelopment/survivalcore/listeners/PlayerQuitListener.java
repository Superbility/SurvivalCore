package com.superdevelopment.survivalcore.listeners;

import com.superdevelopment.survivalcore.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void removeSurvivalPlayer(PlayerQuitEvent e) {
        plugin.survivalPlayers.remove(e.getPlayer().getUniqueId());
    }
}
