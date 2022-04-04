package com.superdevelopment.survivalcore.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.items.MenuItem;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CompassInteractListener implements Listener {
    @EventHandler
    private void onDrop(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        if(item != null && item.getType() != XMaterial.AIR.parseMaterial()) {
            NBTItem nbtItem = new NBTItem(item);

            if (nbtItem.hasKey("SURVIVAL_KEY") && nbtItem.getString("SURVIVAL_KEY").equals(MenuItem.itemKey)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if(item != null && item.getType() != XMaterial.AIR.parseMaterial()) {
            NBTItem nbtItem = new NBTItem(item);

            if (nbtItem.hasKey("SURVIVAL_KEY") && nbtItem.getString("SURVIVAL_KEY").equals(MenuItem.itemKey)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent e) {
        for(ItemStack item : e.getDrops()) {
            NBTItem nbtItem = new NBTItem(item);

            if(nbtItem.hasKey("SURVIVAL_KEY") && nbtItem.getString("SURVIVAL_KEY").equals(MenuItem.itemKey)) {
                e.getDrops().remove(item);
            }
        }
    }

    @EventHandler
    private void onRespawn(PlayerRespawnEvent e) {
        ItemStack menuItem = MenuItem.getMenuItem();
        e.getPlayer().getInventory().setItem(8, menuItem);
    }
}