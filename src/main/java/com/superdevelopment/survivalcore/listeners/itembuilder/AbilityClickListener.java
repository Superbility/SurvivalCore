package com.superdevelopment.survivalcore.listeners.itembuilder;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.data.items.builder.data.Selected;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AbilityClickListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        if (plugin.itemBuildingPlayers.containsKey(e.getWhoClicked().getUniqueId())) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                NBTItem nbtItem = new NBTItem(e.getCurrentItem());
                ItemBuilder itemBuilder = plugin.itemBuildingPlayers.get(e.getWhoClicked().getUniqueId());
                if(itemBuilder.getSelection() == Selected.ABILITY) {
                    if(nbtItem.hasKey("TeleportItem")) {
                        itemBuilder.setAbility(Ability.TELEPORT);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("SoulStealItem")) {
                        itemBuilder.setAbility(Ability.SOUL_STEAL);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("InstantFireItem")) {
                        itemBuilder.setAbility(Ability.INSTANT_FIRE);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("RapidHealItem")) {
                        itemBuilder.setAbility(Ability.RAPID_HEAL);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("MagmaMountainItem")) {
                        itemBuilder.setAbility(Ability.MAGMA_MOUNTAIN);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("BulkUpItem")) {
                        itemBuilder.setAbility(Ability.BULK_UP);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("AssassinStrikeItem")) {
                        itemBuilder.setAbility(Ability.ASSASSIN_STRIKE);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("SpiritWaveItem")) {
                        itemBuilder.setAbility(Ability.SPIRIT_WAVE);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("WitherGlideItem")) {
                        itemBuilder.setAbility(Ability.WITHER_GLIDE);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("EnderManiacItem")) {
                        itemBuilder.setAbility(Ability.ENDER_MANIAC);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    } else if(nbtItem.hasKey("CancelItem")) {
                        itemBuilder.setAbility(null);
                        itemBuilder.refreshInventory();
                        itemBuilder.setSelection(Selected.NONE);
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
