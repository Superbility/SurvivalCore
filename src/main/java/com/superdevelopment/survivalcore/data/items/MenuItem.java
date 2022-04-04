package com.superdevelopment.survivalcore.data.items;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.configs.MenuConfig;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuItem {
    private static MenuConfig menuConfig = new MenuConfig();
    public static String itemKey = "MENU";

    public static ItemStack getMenuItem() {
        Material material = XMaterial.valueOf(menuConfig.getConfig().getString("Item.Material")).parseMaterial();
        String name = ChatColor.colorise(menuConfig.getConfig().getString("Item.Name"));
        List<String> lore = ChatColor.colorise(menuConfig.getConfig().getStringList("Item.Lore"));

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("SURVIVAL_KEY", itemKey);
        ItemStack finalItem = nbtItem.getItem();

        return finalItem;
    }
}
