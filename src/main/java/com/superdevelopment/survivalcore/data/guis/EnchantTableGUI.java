package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.configs.EnchantingConfig;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantTableGUI {
    private static EnchantingConfig enchCfg = new EnchantingConfig();

    private static List<Integer> enchantSlots = new ArrayList<Integer>(Arrays.asList(12, 13, 14, 15, 16, 21, 22, 23, 25, 26, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43));

    public static Inventory getInventory(Inventory inv) {
        ItemStack enchantItem = new ItemStack(XMaterial.ENCHANTING_TABLE.parseMaterial());
        ItemMeta enchantItemMeta = enchantItem.getItemMeta();
        enchantItemMeta.setDisplayName(ChatColor.colorise("&6&lEnchantment Table"));
        enchantItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Add an item to the slot above to view all the enchants", "&7that can be applied to it")));
        enchantItem.setItemMeta(enchantItemMeta);
        NBTItem nbtEnchantItem = new NBTItem(enchantItem);
        nbtEnchantItem.setBoolean("clickable", false);
        enchantItem = nbtEnchantItem.getItem();

        ItemStack fillItem = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta fillItemMeta = fillItem.getItemMeta();
        fillItemMeta.setDisplayName(" ");
        fillItem.setItemMeta(fillItemMeta);
        NBTItem nbtFillItem = new NBTItem(fillItem);
        nbtFillItem.setBoolean("clickable", false);
        fillItem = nbtFillItem.getItem();

        if(inv.getItem(28) != null && !inv.getItem(28).getType().equals(XMaterial.AIR.parseMaterial())) {
            ItemType type = ItemType.getType(inv.getItem(19));

            for (String s : enchCfg.getConfig().getConfigurationSection("").getKeys(false)) {
                Enchant enchant = Enchant.valueOf(s);
                ItemStack item = enchant.getDisplayItem();

                if (enchant.getItemType().equals(type)) {
                    for (Integer i : enchantSlots) {
                        if (inv.getItem(i) == null || inv.getItem(i).isSimilar(fillItem)) {
                            inv.setItem(i, item);
                            break;
                        }
                    }
                }
            }
        }



        inv.setItem(28, enchantItem);
        for (int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, fillItem);
            }
        }
        inv.setItem(19, new ItemStack(XMaterial.AIR.parseMaterial()));

        return inv;
    }
}
