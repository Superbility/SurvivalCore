package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ModeSelectionGUI {
    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, "Select Mode");

        ItemStack fillItem = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta fillItemMeta = fillItem.getItemMeta();
        fillItemMeta.setDisplayName(" ");
        fillItem.setItemMeta(fillItemMeta);
        NBTItem nbtFillItem = new NBTItem(fillItem);
        nbtFillItem.setBoolean("clickable", false);
        fillItem = nbtFillItem.getItem();

        ItemStack normalItem = new ItemStack(XMaterial.WHEAT.parseMaterial());
        ItemMeta normalMeta = normalItem.getItemMeta();
        normalMeta.setDisplayName(ChatColor.colorise("&aNormal Mode"));
        normalMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to start your", "&7profile in normal mode! No", "&7debuffs no nothing - just smooth", "&7sailing :D")));
        normalItem.setItemMeta(normalMeta);
        NBTItem normalNbt = new NBTItem(normalItem);
        normalNbt.setString("mode", "normal");
        normalItem = normalNbt.getItem();

        ItemStack adventureItem = new ItemStack(XMaterial.BONE.parseMaterial());
        ItemMeta adventureMeta = adventureItem.getItemMeta();
        adventureMeta.setDisplayName(ChatColor.colorise("&c&lAdventure Mode"));
        adventureMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to start your", "&7profile in &cAdventure Mode!", "", "&7Adventure mode comes with the", "&7following debuffs:", "&7- 35% slower natural regeneration", "&7- 5% reduced damage", "&7- 10% more damage taken", "", "&7Players with adventure mode will", "&7receive the &8[&a✹&8] &7tag before", "&7their name to show off their mastery", "&7of the game!")));
        adventureItem.setItemMeta(adventureMeta);
        NBTItem adventureNbt = new NBTItem(adventureItem);
        adventureNbt.setString("mode", "adventure");
        adventureItem = adventureNbt.getItem();

        inv.setItem(12, normalItem);
        inv.setItem(14, adventureItem);
        for (int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, fillItem);
            }
        }

        return inv;
    }
}
