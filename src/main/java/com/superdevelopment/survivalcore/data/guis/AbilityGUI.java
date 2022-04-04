package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AbilityGUI {
    public static Inventory getInventory(ItemBuilder builder) {
        Inventory inv = Bukkit.createInventory(null, 45, "Ability Menu");

        ItemType type = ItemType.getType(builder.getItem());

        ItemStack teleportItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta teleportItemMeta = teleportItem.getItemMeta();
        teleportItemMeta.setDisplayName(ChatColor.colorise("&6&lTeleport Ability"));
        teleportItemMeta.setLore(Ability.TELEPORT.getDescription());
        teleportItem.setItemMeta(teleportItemMeta);
        if(type.equals(ItemType.SWORD)) {
            teleportItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(teleportItem);
            nbtItem.setBoolean("TeleportItem", true);
            teleportItem = nbtItem.getItem();
        }

        ItemStack soulStealItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta soulStealItemMeta = soulStealItem.getItemMeta();
        soulStealItemMeta.setDisplayName(ChatColor.colorise("&6&lSoul Steal Ability"));
        soulStealItemMeta.setLore(Ability.SOUL_STEAL.getDescription());
        soulStealItem.setItemMeta(soulStealItemMeta);
        if(type.equals(ItemType.SWORD)) {
            soulStealItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(soulStealItem);
            nbtItem.setBoolean("SoulStealItem", true);
            soulStealItem = nbtItem.getItem();
        }

        ItemStack instantFireItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta instantFireItemMeta = instantFireItem.getItemMeta();
        instantFireItemMeta.setDisplayName(ChatColor.colorise("&6&lInstant Fire Ability"));
        instantFireItemMeta.setLore(Ability.INSTANT_FIRE.getDescription());
        instantFireItem.setItemMeta(instantFireItemMeta);
        if(type.equals(ItemType.BOW)) {
            instantFireItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(instantFireItem);
            nbtItem.setBoolean("InstantFireItem", true);
            instantFireItem = nbtItem.getItem();
        }

        ItemStack rapidHealItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta rapidHealItemMeta = rapidHealItem.getItemMeta();
        rapidHealItemMeta.setDisplayName(ChatColor.colorise("&6&lRapid Heal Ability"));
        rapidHealItemMeta.setLore(Ability.RAPID_HEAL.getDescription());
        rapidHealItem.setItemMeta(rapidHealItemMeta);
        if(type.equals(ItemType.SWORD)) {
            rapidHealItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(rapidHealItem);
            nbtItem.setBoolean("RapidHealItem", true);
            rapidHealItem = nbtItem.getItem();
        }

        ItemStack magmaMountainItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta magmaMountainItemMeta = magmaMountainItem.getItemMeta();
        magmaMountainItemMeta.setDisplayName(ChatColor.colorise("&6&lMagma Mountain Ability"));
        magmaMountainItemMeta.setLore(Ability.MAGMA_MOUNTAIN.getDescription());
        magmaMountainItem.setItemMeta(magmaMountainItemMeta);
        if(type.equals(ItemType.SWORD)) {
            magmaMountainItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(magmaMountainItem);
            nbtItem.setBoolean("MagmaMountainItem", true);
            magmaMountainItem = nbtItem.getItem();
        }

        ItemStack bulkUpItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta bulkUpItemMeta = bulkUpItem.getItemMeta();
        bulkUpItemMeta.setDisplayName(ChatColor.colorise("&6&lBulk Up Ability"));
        bulkUpItemMeta.setLore(Ability.BULK_UP.getDescription());
        bulkUpItem.setItemMeta(bulkUpItemMeta);
        if(type.equals(ItemType.SWORD)) {
            bulkUpItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(bulkUpItem);
            nbtItem.setBoolean("BulkUpItem", true);
            bulkUpItem = nbtItem.getItem();
        }

        ItemStack assassinStrikeItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta assassinStrikeItemMeta = assassinStrikeItem.getItemMeta();
        assassinStrikeItemMeta.setDisplayName(ChatColor.colorise("&6&lAssassin Strike Ability"));
        assassinStrikeItemMeta.setLore(Ability.ASSASSIN_STRIKE.getDescription());
        assassinStrikeItem.setItemMeta(assassinStrikeItemMeta);
        if(type.equals(ItemType.SWORD)) {
            assassinStrikeItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(assassinStrikeItem);
            nbtItem.setBoolean("AssassinStrikeItem", true);
            assassinStrikeItem = nbtItem.getItem();
        }

        ItemStack spiritWaveItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta spiritWaveItemMeta = spiritWaveItem.getItemMeta();
        spiritWaveItemMeta.setDisplayName(ChatColor.colorise("&6&lSpirit Wave Ability"));
        spiritWaveItemMeta.setLore(Ability.SPIRIT_WAVE.getDescription());
        spiritWaveItem.setItemMeta(spiritWaveItemMeta);
        if(type.equals(ItemType.SWORD)) {
            spiritWaveItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(spiritWaveItem);
            nbtItem.setBoolean("SpiritWaveItem", true);
            spiritWaveItem = nbtItem.getItem();
        }

        ItemStack witherGlideItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta witherGlideItemMeta = witherGlideItem.getItemMeta();
        witherGlideItemMeta.setDisplayName(ChatColor.colorise("&6&lWither Glide Ability"));
        witherGlideItemMeta.setLore(Ability.WITHER_GLIDE.getDescription());
        witherGlideItem.setItemMeta(witherGlideItemMeta);
        if(type.equals(ItemType.SWORD)) {
            witherGlideItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(witherGlideItem);
            nbtItem.setBoolean("WitherGlideItem", true);
            witherGlideItem = nbtItem.getItem();
        }

        ItemStack enderManiacItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        ItemMeta enderManiacItemMeta = enderManiacItem.getItemMeta();
        enderManiacItemMeta.setDisplayName(ChatColor.colorise("&6&lEnder Maniac Ability"));
        enderManiacItemMeta.setLore(Ability.ENDER_MANIAC.getDescription());
        enderManiacItem.setItemMeta(enderManiacItemMeta);
        if(type.equals(ItemType.SWORD)) {
            enderManiacItem.setType(XMaterial.NETHER_STAR.parseMaterial());
            NBTItem nbtItem = new NBTItem(enderManiacItem);
            nbtItem.setBoolean("EnderManiacItem", true);
            enderManiacItem = nbtItem.getItem();
        }

        ItemStack cancelItem = new ItemStack(XMaterial.RED_DYE.parseMaterial());
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();
        cancelItemMeta.setDisplayName(ChatColor.colorise("&6&lRemove Ability"));
        cancelItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to remove ability", "&7or cancel selection!")));
        cancelItem.setItemMeta(cancelItemMeta);
        NBTItem nbtCancelItem = new NBTItem(cancelItem);
        nbtCancelItem.setBoolean("CancelItem", true);
        cancelItem = nbtCancelItem.getItem();

        inv.setItem(10, teleportItem);
        inv.setItem(11, soulStealItem);
        inv.setItem(12, rapidHealItem);

        inv.setItem(19, magmaMountainItem);
        inv.setItem(20, bulkUpItem);
        inv.setItem(21, assassinStrikeItem);
        inv.setItem(22, instantFireItem);

        inv.setItem(28, spiritWaveItem);
        inv.setItem(29, witherGlideItem);
        inv.setItem(30, enderManiacItem);

        inv.setItem(24, cancelItem);

        return inv;
    }
}
