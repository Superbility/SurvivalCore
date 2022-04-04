package com.superdevelopment.survivalcore.listeners.inventorylisteners.vault;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.VaultsConfig;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaultCloseListener implements Listener {
    @EventHandler
    private void onClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().contains("Vault") && !e.getView().getTitle().equals("Vaults")) {
            int vault = Integer.valueOf(e.getView().getTitle().replace("Vault ", ""));

            SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getPlayer());
            VaultsConfig vCfg = sPlayer.getVaultConfig();

            List<Map> items = new ArrayList<Map>();
            if(e.getInventory().getContents().length > 0) {
                for(ItemStack item : e.getInventory().getContents()) {
                    if(item != null && item.getType() != Material.AIR) {
                        items.add(item.serialize());
                    }
                }
            }

            vCfg.getConfig().set("Vaults." + vault + ".Items", items);
            vCfg.saveFile();
        }
    }
}
