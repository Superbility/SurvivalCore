package com.superdevelopment.survivalcore.listeners.enchanting;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.guis.EnchantItemsGUI;
import com.superdevelopment.survivalcore.data.guis.EnchantTableGUI;
import com.superdevelopment.survivalcore.data.items.builder.LoreBuilder;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class EnchantingInvClickListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getWhoClicked());
        if(player.getEnchantmentTable() != null) {
            if(e.getCurrentItem() != null) {
                NBTItem item = new NBTItem(e.getCurrentItem());
                if(item.hasKey("clickable") && !item.getBoolean("clickable")) {
                    e.setCancelled(true);
                }

                if(item.hasKey("enchant")) { //Check if selected enchant is an enchant item
                    e.setCancelled(true); //Cancel click
                    Enchant enchant = item.getObject("enchant", Enchant.class); //Get enchant of selected enchant
                    if(item.hasKey("tier")) { //Check if it is an enchant item
                        int tier = item.getInteger("tier"); //Get tier
                        int cost = enchant.getCost(tier); //Get cost
                        if(Enchant.canEnchant(player.getEnchantmentTable().getItem(), enchant, tier, player)) {
                            ItemStack enchantedItem = Enchant.addEnchant(player.getEnchantmentTable().getItem(), enchant, tier);
                            if(enchant == Enchant.RAW_STRENGTH) {
                                NBTItem i = new NBTItem(enchantedItem);
                                i.setInteger("ItemBaseDamage", i.getInteger("ItemBaseDamage") + (5 * tier));
                                enchantedItem = i.getItem();

                                ItemMeta meta = enchantedItem.getItemMeta();
                                meta.setLore(new LoreBuilder(enchantedItem).buildLore());
                                enchantedItem.setItemMeta(meta);
                            }

                            player.getEnchantmentTable().setItem(enchantedItem);
                            player.getPlayer().setLevel(player.getPlayer().getLevel() - cost);
                            player.closeEnchantmentTable();
                        } else {
                            player.closeEnchantmentTable();
                        }
                        return;
                    } else {
                        EnchantItemsGUI.setItems(player.getEnchantmentTable().getInventory(), enchant);
                        return;
                    }
                }

                if(item.hasKey("back")) {
                    e.setCancelled(true);
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.getEnchantmentTable().updateInventory(false);
                        }
                    }.runTaskLaterAsynchronously(plugin, 2);
                    return;
                }
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    player.getEnchantmentTable().updateInventory(true);
                }
            }.runTaskLaterAsynchronously(plugin, 2);
        }
    }
}