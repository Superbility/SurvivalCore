package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantItemsGUI {
    public static void setItems(Inventory inv, Enchant enchant) {
        ItemStack fillItem = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta fillItemMeta = fillItem.getItemMeta();
        fillItemMeta.setDisplayName(" ");
        fillItem.setItemMeta(fillItemMeta);
        NBTItem nbtFillItem = new NBTItem(fillItem);
        nbtFillItem.setBoolean("clickable", false);
        fillItem = nbtFillItem.getItem();

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fillItem);
        }

        int tier = enchant.getEnchantTableTier();

        if(tier == 5) {
            inv.setItem(20, enchant.getItem(1, true));
            inv.setItem(21, enchant.getItem(2, true));
            inv.setItem(22, enchant.getItem(3, true));
            inv.setItem(23, enchant.getItem(4, true));
            inv.setItem(24, enchant.getItem(5, true));
        }
        if(tier == 4) {
            inv.setItem(19, enchant.getItem(1, true));
            inv.setItem(21, enchant.getItem(2, true));
            inv.setItem(23, enchant.getItem(3, true));
            inv.setItem(25, enchant.getItem(4, true));
        }
        if(tier == 3) {
            inv.setItem(20, enchant.getItem(1, true));
            inv.setItem(22, enchant.getItem(2, true));
            inv.setItem(24, enchant.getItem(3, true));
        }

        ItemStack back = new ItemStack(XMaterial.ARROW.parseMaterial());
        ItemMeta meta = back.getItemMeta();
        meta.setDisplayName(ChatColor.colorise("&c&lBack"));
        back.setItemMeta(meta);
        NBTItem nbtBack = new NBTItem(back);
        nbtBack.setBoolean("back", true);
        back = nbtBack.getItem();

        inv.setItem(45, back);
    }
}
