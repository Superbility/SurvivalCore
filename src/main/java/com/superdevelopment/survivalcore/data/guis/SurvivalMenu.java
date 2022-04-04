package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.MenuConfig;
import com.superdevelopment.survivalcore.utils.ChatColor;
import com.superdevelopment.survivalcore.utils.SetPlaceholders;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SurvivalMenu {
    private Main plugin = Main.getPlugin(Main.class);
    private MenuConfig menuCfg = new MenuConfig();

    public Inventory getMainMenu() {
        int size = menuCfg.getConfig().getInt("Menus.MainMenu.Size");
        String invName = menuCfg.getConfig().getString("Menus.MainMenu.Name");

        Inventory inv = Bukkit.createInventory(null, size, ChatColor.colorise(invName));

        for(String s : menuCfg.getConfig().getConfigurationSection("Menus.MainMenu.Slot").getKeys(false)) {
            String clickedLocation = menuCfg.getConfig().getString("Menus.MainMenu.Slot." + s + ".ClickedLocation");
            Material mat = XMaterial.valueOf(menuCfg.getConfig().getString("Menus.MainMenu.Slot." + s + ".Material")).parseMaterial();
            String name = menuCfg.getConfig().getString("Menus.MainMenu.Slot." + s + ".Name");
            List<String> lore = menuCfg.getConfig().getStringList("Menus.MainMenu.Slot." + s + ".Lore");
            int slot = Integer.valueOf(s);

            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.colorise(name));
            meta.setLore(ChatColor.colorise(lore));
            item.setItemMeta(meta);

            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setString("ClickedLocation", clickedLocation);
            ItemStack finalItem = nbtItem.getItem();

            inv.setItem(slot, finalItem);
        }
        return inv;
    }
    public Inventory getStatsMenu(Player player) {
        SurvivalPlayer sPlayer = plugin.survivalPlayers.get(player.getUniqueId());

        int size = menuCfg.getConfig().getInt("Menus.Stats.Size");
        String invName = menuCfg.getConfig().getString("Menus.Stats.Name");

        Inventory inv = Bukkit.createInventory(null, size, invName);

        for(String s : menuCfg.getConfig().getConfigurationSection("Menus.Stats.Slot").getKeys(false)) {
            Material mat = XMaterial.valueOf(menuCfg.getConfig().getString("Menus.Stats.Slot." + s + ".Material")).parseMaterial();
            String name = menuCfg.getConfig().getString("Menus.Stats.Slot." + s + ".Name");
            List<String> lore = SetPlaceholders.setPlaceholders(menuCfg.getConfig().getStringList("Menus.Stats.Slot." + s + ".Lore"), sPlayer);
            int slot = Integer.valueOf(s);

            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.colorise(name));
            meta.setLore(ChatColor.colorise(lore));
            item.setItemMeta(meta);

            inv.setItem(slot, item);
        }
        return inv;
    }
}
