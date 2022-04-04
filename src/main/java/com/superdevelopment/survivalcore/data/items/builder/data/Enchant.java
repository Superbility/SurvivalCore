package com.superdevelopment.survivalcore.data.items.builder.data;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.EnchantingConfig;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.data.items.builder.LoreBuilder;
import com.superdevelopment.survivalcore.utils.ChatColor;
import com.superdevelopment.survivalcore.utils.SetPlaceholders;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public enum Enchant {
    LIFE_ENERGY, VAMPIRE, CRITICAL_STRIKE, END_STRIKE, RAW_STRENGTH, WITHER_GUARD, PROTECTION, SMITE, BOA, SHARPNESS;

    private EnchantingConfig enchCfg = new EnchantingConfig();
    private static Main plugin = Main.getPlugin(Main.class);

    public ItemType getItemType() {
        switch (this) {
            case LIFE_ENERGY:
            case WITHER_GUARD:
            case PROTECTION:
                return ItemType.ARMOR;
            case VAMPIRE:
            case CRITICAL_STRIKE:
            case END_STRIKE:
            case RAW_STRENGTH:
            case SMITE:
            case BOA:
            case SHARPNESS:
                return ItemType.SWORD;
        }
        return null;
    }
    public String getName() {
        switch(this) {
            case LIFE_ENERGY: return "Life Energy {tier}";
            case WITHER_GUARD: return "Wither Guard {tier}";
            case PROTECTION: return "Protection {tier}";
            case VAMPIRE: return "Vampire {tier}";
            case CRITICAL_STRIKE: return "Critical Strike {tier}";
            case END_STRIKE: return "End Strike {tier}";
            case RAW_STRENGTH: return "Raw Strength {tier}";
            case SMITE: return "Smite {tier}";
            case BOA: return "Bane of Arthropods {tier}";
            case SHARPNESS: return "Sharpness {tier}";
        }
        return "null";
    }
    public ItemStack getDisplayItem() {
        ItemStack item = new ItemStack(XMaterial.ENCHANTED_BOOK.parseMaterial());
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.colorise(enchCfg.getConfig().getString(this.toString() + ".GUI.Name")));
        itemMeta.setLore(ChatColor.colorise(enchCfg.getConfig().getStringList(this.toString() + ".GUI.Lore")));
        item.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("enchant", this.toString());
        item = nbtItem.getItem();

        return item;
    }
    public ItemStack getItem(int tier, boolean cost) {
        ItemStack item = new ItemStack(XMaterial.ENCHANTED_BOOK.parseMaterial());
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.colorise(enchCfg.getConfig().getString(this.toString() + ".Name")).replace("{tier}", String.valueOf(tier)));
        itemMeta.setLore(ChatColor.colorise(getLore(tier, cost)));
        item.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setObject("enchant", this);
        nbtItem.setInteger("tier", tier);
        item = nbtItem.getItem();

        return item;
    }
    private List<String> getLore(int tier, boolean cost) {
        List<String> lore = enchCfg.getConfig().getStringList(this.toString() + ".Lore");
        if(cost) {
            lore.add("");
            lore.add("&c&lCost: " + this.getCost(tier));
        }
        switch (this) {
            case LIFE_ENERGY:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(20 * tier));
            case VAMPIRE:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(0.5 * tier));
            case CRITICAL_STRIKE:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(5 * tier));
            case END_STRIKE:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(10 * tier));
            case RAW_STRENGTH:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(5 * tier));
            case WITHER_GUARD:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(5 * tier));
            case PROTECTION:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(5 * tier));
            case SHARPNESS:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(10 * tier));
            case SMITE:
            case BOA:
                lore = SetPlaceholders.setCustomPlaceholder(lore, "{amount}", String.valueOf(15 * tier));
        }
        return lore;
    }

    public int getEnchantTableTier() {
        switch(this) {
            case LIFE_ENERGY: return 5;
            case VAMPIRE: return 3;
            case CRITICAL_STRIKE: return 5;
            case END_STRIKE: return 5;
            case RAW_STRENGTH: return 5;
            case WITHER_GUARD: return 0;
            case PROTECTION: return 4;
            case SHARPNESS: return 5;
            case SMITE: return 5;
            case BOA: return 5;
        }
        return 0;
    }
    public int getCost(int tier) {
        return enchCfg.getConfig().getInt(this.toString() + ".Cost." + tier);
    }
    public static ItemStack addEnchant(ItemStack item, Enchant enchant, int tier) {
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey("enchants")) {
            HashMap<Enchant, Integer> enchants = getItemEnchants(item);

            if(enchant == Enchant.SHARPNESS || enchant == Enchant.SMITE || enchant == enchant.BOA) {
                if(enchants.containsKey(Enchant.BOA)) enchants.remove(Enchant.BOA);
                if(enchants.containsKey(Enchant.SMITE)) enchants.remove(Enchant.SMITE);
                if(enchants.containsKey(Enchant.SHARPNESS)) enchants.remove(Enchant.SHARPNESS);
            }

            if(enchants.containsKey(enchant)) {
                enchants.replace(enchant, tier);
            } else {
                enchants.put(enchant, tier);
            }
            nbtItem.setObject("enchants", enchants);
        } else {
            HashMap<Enchant, Integer> enchants = new HashMap<>();
            enchants.put(enchant, tier);
            nbtItem.setObject("enchants", enchants);
        }

        ItemStack finalItem = nbtItem.getItem();
        finalItem.addUnsafeEnchantment(Enchantment.LURE, 1);
        ItemMeta finalItemMeta = finalItem.getItemMeta();
        finalItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        finalItemMeta.setLore(new LoreBuilder(finalItem).buildLore());
        finalItem.setItemMeta(finalItemMeta);

        return finalItem;
    }
    public static HashMap<Enchant, Integer> getItemEnchants(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        HashMap<String, Double> enchants = nbtItem.getObject("enchants", HashMap.class);

        HashMap<Enchant, Integer> newEnchants = new HashMap<>();

        if(nbtItem.hasKey("enchants")) {
            for(String s : enchants.keySet()) {
                Enchant e = Enchant.valueOf(s);
                double dTier = enchants.get(s);
                int tier = (int) dTier;

                newEnchants.put(e, tier);
            }
        }
        return newEnchants;
    }
    public static boolean canEnchant(ItemStack item, Enchant enchant, int tier, SurvivalPlayer player) {
        HashMap<Enchant, Integer> enchants = getItemEnchants(item);

        if(enchants != null) {
            int cost = enchant.getCost(tier);
            if(player.getPlayer().getLevel() >= cost) {
                if (enchants.containsKey(enchant)) {
                    if (enchants.get(enchant) > tier) {
                        player.getPlayer().sendMessage(ChatColor.colorise("&cThis item already has this enchant in a higher tier!"));
                        player.closeEnchantmentTable();
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                player.getPlayer().sendMessage(ChatColor.colorise("&cYou do not have enough XP!"));
                player.closeEnchantmentTable();
                return false;
            }
        } else {
            return true;
        }
    }
    public static boolean canEnchant(ItemStack item, Enchant enchant, int tier) {
        HashMap<Enchant, Integer> enchants = getItemEnchants(item);

        if (enchants != null) {
            if (enchants.containsKey(enchant)) {
                if (enchants.get(enchant) > tier) {
                    return false;
                } else {
                    if(ItemType.getType(item) == enchant.getItemType()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public void addStaticEnchants(SurvivalPlayer player, int tier) {
        switch (this) {
            case LIFE_ENERGY:
                player.addMaxHealth(tier * 20);
            case PROTECTION:
                player.addDefense(tier * 5);
        }
    }
    public void removeStaticEnchants(SurvivalPlayer player, int tier) {
        switch (this) {
            case LIFE_ENERGY:
                player.removeMaxHealth(tier * 20);
            case PROTECTION:
                player.removeDefense(tier * 5);
        }
    }
}
