package com.superdevelopment.survivalcore.listeners.anvil;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.guis.EnchantItemsGUI;
import com.superdevelopment.survivalcore.data.items.builder.Anvil;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AnvilInvClickListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getWhoClicked());
        if (player.getAnvil() != null) {
            if (e.getCurrentItem() != null) {
                Anvil anvil = player.getAnvil();

                NBTItem item = new NBTItem(e.getCurrentItem());
                if (item.hasKey("clickable") && !item.getBoolean("clickable")) {
                    e.setCancelled(true);
                }
                if(item.hasKey("buy")) {
                    ItemStack leftItem = anvil.getLeftItem();
                    ItemStack rightItem = anvil.getRightItem();

                    Enchant enchant;
                    int tier;

                    if (leftItem != null && rightItem != null) {
                        NBTItem rightItemNbt = new NBTItem(rightItem);
                        if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                            enchant = rightItemNbt.getObject("enchant", Enchant.class);
                            tier = rightItemNbt.getInteger("tier");
                            if (Enchant.canEnchant(leftItem, enchant, tier)) {
                                if(player.getPlayer().getLevel() >= enchant.getCost(tier)) {
                                    player.getPlayer().setLevel(player.getPlayer().getLevel() - enchant.getCost(tier));
                                    ItemStack finalItem = Enchant.addEnchant(leftItem, enchant, tier);
                                    anvil.setFinalItem(finalItem);
                                    anvil.setLeftItem(null);
                                    anvil.setRightItem(null);
                                } else {
                                    player.getPlayer().sendMessage(ChatColor.colorise("&cYou cannot afford to apply this enchant!"));
                                }
                            } else {
                                player.getPlayer().sendMessage(ChatColor.colorise("&cThis enchant cannot be applied to this item!"));
                            }
                        }
                    }
                }
                if(e.getSlot() == 13) {
                    if(!e.isCancelled()) {
                        anvil.setFinalItem(null);
                    }
                }

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        anvil.updateInventory();
                    }
                }.runTaskLaterAsynchronously(plugin, 6);
            }
        }
    }
}
