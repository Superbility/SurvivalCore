package com.superdevelopment.survivalcore.listeners;

import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerDamageItemListener implements Listener {
    @EventHandler
    private void onItemDamge(PlayerItemDamageEvent e) {
        NBTItem item = new NBTItem(e.getItem());
        if(item.hasKey("Uses")) {
            int maxUses = item.getInteger("MaxUses");
            int uses = item.getInteger("Uses");

            uses--;

            if(uses <= 0) {
                e.setDamage(1000000);
                return;
            }

            int slot = getSlot(e.getPlayer(), e.getItem());

            item.setInteger("Uses", uses);
            ItemStack finalItem = item.getItem();
            ItemMeta finalItemMeta = finalItem.getItemMeta();
            finalItemMeta.setLore(changeLore(finalItem.getItemMeta().getLore(), uses, maxUses));
            finalItem.setItemMeta(finalItemMeta);

            e.getPlayer().getInventory().setItem(slot, finalItem);

            e.setCancelled(true);
        }
    }
    private int getSlot(Player player, ItemStack item) {
        for(int i = 0; i < player.getInventory().getSize(); ++i) {
            if(player.getInventory().getItem(i).equals(item)) {
                return i;
            }
        }
        return 0;
    }
    private List<String> changeLore(List<String> lore, int uses, int maxUses) {
        List<String> newLore = lore;
        newLore.set(newLore.size() - 3, ChatColor.colorise("&7Hits remaining: &c" + uses + "&7/&c" + maxUses));
        return newLore;
    }
}
