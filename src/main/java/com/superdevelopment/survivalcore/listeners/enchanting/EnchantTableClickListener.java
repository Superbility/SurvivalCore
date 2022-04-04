package com.superdevelopment.survivalcore.listeners.enchanting;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.builder.EnchantmentTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
public class EnchantTableClickListener implements Listener {

    @EventHandler
    private void onClick(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getClickedBlock().getType().equals(XMaterial.ENCHANTING_TABLE.parseMaterial())) {
                e.setCancelled(true);
                SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
                EnchantmentTable eTable = new EnchantmentTable(sPlayer);
                sPlayer.setEnchantmentTable(eTable);
            }
        }
    }
}
