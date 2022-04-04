package com.superdevelopment.survivalcore.listeners.inventorylisteners;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.guis.SurvivalMenu;
import com.superdevelopment.survivalcore.data.guis.VaultsGUI;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SurvivalMenuClickListener implements Listener {
    private SurvivalMenu survivalMenu = new SurvivalMenu();

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.getType() != XMaterial.AIR.parseMaterial()) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey("ClickedLocation")) {
                if (nbtItem.getString("ClickedLocation").equals("STATS")) {
                    e.setCancelled(true);
                    e.getWhoClicked().openInventory(survivalMenu.getStatsMenu((Player) e.getWhoClicked()));
                }
                if(nbtItem.getString("ClickedLocation").equals("VAULTS")) {
                    e.setCancelled(true);
                    e.getWhoClicked().openInventory(VaultsGUI.getInventory((Player) e.getWhoClicked()));
                }
                if(nbtItem.getString("ClickedLocation").equals("LEVELS")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}