package com.superdevelopment.survivalcore.listeners.itembuilder;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.guis.AbilityGUI;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.data.items.builder.data.Selected;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemBuilderClickListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        if(plugin.itemBuildingPlayers.containsKey(e.getWhoClicked().getUniqueId())) {
            if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                NBTItem nbtItem = new NBTItem(e.getCurrentItem());
                ItemBuilder itemBuilder = plugin.itemBuildingPlayers.get(e.getWhoClicked().getUniqueId());
                if(nbtItem.hasKey("RarityItem")) {
                    itemBuilder.setSelection(Selected.RARITY);
                    itemBuilder.scrollRarity();
                } else if(nbtItem.hasKey("NameItem")) {
                    itemBuilder.setSelection(Selected.NAME);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("UsesItem")) {
                    itemBuilder.setSelection(Selected.USES);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("HealthItem")) {
                    itemBuilder.setSelection(Selected.HEALTH);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("DefenseItem")) {
                    itemBuilder.setSelection(Selected.DEFENSE);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("IntelligenceItem")) {
                    itemBuilder.setSelection(Selected.INTELLIGENCE);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("ForceItem")) {
                    itemBuilder.setSelection(Selected.FORCE);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("CritDamageItem")) {
                    itemBuilder.setSelection(Selected.CRITDAMAGE);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("LuckyChanceItem")) {
                    itemBuilder.setSelection(Selected.LUCKYCHANCE);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("SpeedItem")) {
                    itemBuilder.setSelection(Selected.SPEED);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("AgilityItem")) {
                    itemBuilder.setSelection(Selected.AGILITY);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                } else if(nbtItem.hasKey("AbilityItem")) {
                    itemBuilder.setSelection(Selected.ABILITY);
                    e.setCancelled(true);
                    e.getWhoClicked().openInventory(AbilityGUI.getInventory(itemBuilder));
                } else if(nbtItem.hasKey("BaseDamageItem")) {
                    itemBuilder.setSelection(Selected.BASEDAMAGE);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                }
                else if(e.getSlot() == 13) {
                    e.getWhoClicked().setItemInHand(itemBuilder.getItem());
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                    plugin.itemBuildingPlayers.remove(e.getWhoClicked().getUniqueId());
                }
            }
        }
    }
}
