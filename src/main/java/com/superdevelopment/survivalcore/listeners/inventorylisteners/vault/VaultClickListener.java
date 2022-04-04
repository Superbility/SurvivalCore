package com.superdevelopment.survivalcore.listeners.inventorylisteners.vault;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.VaultsConfig;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaultClickListener implements Listener {

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.getType() != XMaterial.AIR.parseMaterial()) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey("Vault")) {
                SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getWhoClicked());
                VaultsConfig vCfg = sPlayer.getVaultConfig();

                int vault = nbtItem.getInteger("Vault");

                List<Map> list = (List<Map>) vCfg.getConfig().getList("Vaults." + vault + ".Items");

                Inventory vaultInventory = Bukkit.createInventory(null, 54, "Vault " + vault);

                if(!list.isEmpty()) {
                    for (Map<String, Object> map : list) {
                        vaultInventory.addItem(ItemStack.deserialize(map));
                    }
                }

                e.getWhoClicked().openInventory(vaultInventory);
            }
        }
    }
}