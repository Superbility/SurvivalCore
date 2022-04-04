package com.superdevelopment.survivalcore.listeners.anvil;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.builder.Anvil;
import com.superdevelopment.survivalcore.data.items.builder.EnchantmentTable;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AnvilClickListener implements Listener {
    @EventHandler
    private void onClick(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getClickedBlock().getType().equals(XMaterial.ANVIL.parseMaterial()) || e.getClickedBlock().getType().equals(XMaterial.CHIPPED_ANVIL.parseMaterial()) || e.getClickedBlock().getType().equals(XMaterial.DAMAGED_ANVIL.parseMaterial())) {
                e.setCancelled(true);
                SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
                Anvil anvil = new Anvil(sPlayer);
                sPlayer.setAnvil(anvil);
            }
        }
    }
}
