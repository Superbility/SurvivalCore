package com.superdevelopment.survivalcore.data.items.builder;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.EnchantingConfig;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantmentTable {
    private EnchantingConfig enchCfg = new EnchantingConfig();

    private SurvivalPlayer player;
    private ItemStack item;
    private Inventory inv;
    private boolean invOpen = false;
    private List<Enchant> applicableEnchants = new ArrayList<>();

    public EnchantmentTable(SurvivalPlayer player) {
        this.player = player;
        this.inv = Bukkit.createInventory(null, 54, "Enchant Table");
        this.item = null;
        updateInventory(true);
        this.player.getPlayer().openInventory(inv);
    }

    public Inventory getInventory() {
        return this.inv;
    }

    public void updateItem() {
        this.item = this.inv.getItem(19);
    }
    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void updateApplicableEnchants() {
        this.applicableEnchants.clear();
        if(item != null) {
            ItemType type = ItemType.getType(item);
            for (String s : enchCfg.getConfig().getConfigurationSection("").getKeys(false)) {
                Enchant enchant = Enchant.valueOf(s);
                if (enchant.getItemType().equals(type)) {
                    this.applicableEnchants.add(enchant);
                }
            }
        }
    }

    private static List<Integer> enchantSlots = new ArrayList<Integer>(Arrays.asList(12, 13, 14, 15, 16, 21, 22, 23, 25, 26, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43));
    public void updateInventory(boolean checkItem) {
        if (checkItem) updateItem();

        inv.clear();

        ItemStack enchantItem = new ItemStack(XMaterial.ENCHANTING_TABLE.parseMaterial());
        ItemMeta enchantItemMeta = enchantItem.getItemMeta();
        enchantItemMeta.setDisplayName(ChatColor.colorise("&aEnchant Item"));
        enchantItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Add an item to the slot above", "&7to view enchantment options!")));
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

        updateApplicableEnchants();

        if (item != null) {
            for (Enchant enchant : this.applicableEnchants) {
                if(enchant.getEnchantTableTier() > 0) {
                    for (Integer i : enchantSlots) {
                        if (inv.getItem(i) == null || inv.getItem(i).isSimilar(fillItem)) {
                            inv.setItem(i, enchant.getDisplayItem());
                            break;
                        }
                    }
                }
            }
        }

        if(this.item != null) inv.setItem(19, this.item);

        inv.setItem(28, enchantItem);
        for (int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                if(i != 19) {
                    inv.setItem(i, fillItem);
                }
            }
        }

        this.player.getPlayer().sendMessage(String.valueOf(this.applicableEnchants));
    }

    public ItemStack getItem() {
        return this.item;
    }
}
