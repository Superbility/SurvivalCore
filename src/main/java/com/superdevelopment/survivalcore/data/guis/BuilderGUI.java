package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.data.items.builder.data.Rarity;
import com.superdevelopment.survivalcore.utils.AddLists;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuilderGUI {
    public static Inventory getInventory(ItemBuilder builder) {
        Inventory inv = Bukkit.createInventory(null, 54, "Item Builder");

        Rarity itemRarity = builder.getRarity();
        int usesAmount = builder.getUses();
        //String name = builder.getName();
        //List<String> lore = builder.getLore();

        int healthAmount = builder.getHealth();
        int defenseAmount = builder.getDefense();
        int intelligenceAmount = builder.getIntelligence();
        int forceAmount = builder.getForce();
        int critDamageAmount = builder.getCritDamage();
        int luckyChanceAmount = builder.getLuckyChance();
        int speedAmount = builder.getSpeed();
        int agilityAmount = builder.getAgility();
        Ability ability = builder.getAbility();

        String abilityName;
        if(ability != null) abilityName = ability.getName();
        else abilityName = "None!";

        ItemStack rarity = new ItemStack(XMaterial.DIAMOND.parseMaterial());
        ItemMeta rarityMeta = rarity.getItemMeta();
        rarityMeta.setDisplayName(ChatColor.colorise("&6&lItem Rarity"));
        rarityMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click to shuffle through item rarities!", "", Rarity.COMMON.getSelectedRarity(itemRarity), Rarity.UNCOMMON.getSelectedRarity(itemRarity), Rarity.RARE.getSelectedRarity(itemRarity), Rarity.VERY_RARE.getSelectedRarity(itemRarity), Rarity.EXTREMELY_RARE.getSelectedRarity(itemRarity), Rarity.LEGENDARY.getSelectedRarity(itemRarity))));
        rarity.setItemMeta(rarityMeta);
        NBTItem nbtRarity = new NBTItem(rarity);
        nbtRarity.setBoolean("RarityItem", true);
        rarity = nbtRarity.getItem();

        ItemStack uses = new ItemStack(XMaterial.ANVIL.parseMaterial());
        ItemMeta usesMeta = uses.getItemMeta();
        usesMeta.setDisplayName(ChatColor.colorise("&6&lItem Uses"));
        usesMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to set the maximum", "&7amount of uses this item has!", "", "&7Current uses: " + usesAmount)));
        uses.setItemMeta(usesMeta);
        NBTItem nbtUses = new NBTItem(uses);
        nbtUses.setBoolean("UsesItem", true);
        uses = nbtUses.getItem();

        /*
        ItemStack nameItem = new ItemStack(XMaterial.NAME_TAG.parseMaterial());
        ItemMeta nameItemMeta = nameItem.getItemMeta();
        nameItemMeta.setDisplayName(ChatColor.colorise("&7Item Name"));
        nameItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to set the name", "&7of this item!", "", "&7Current name: &a" + name)));
        nameItem.setItemMeta(nameItemMeta);
        NBTItem nbtName = new NBTItem(nameItem);
        nbtName.setBoolean("NameItem", true);
        nameItem = nbtName.getItem();

        ItemStack loreItem = new ItemStack(XMaterial.WRITABLE_BOOK.parseMaterial());
        ItemMeta loreItemMeta = loreItem.getItemMeta();
        loreItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Lore"));
        loreItemMeta.setLore(ChatColor.colorise(AddLists.addList(Arrays.asList("&7Click here to set the lore", "&7of this item!Enter as many lines", "&7as you need and then type &a'cancel'", "&7to stop!", "", "&7Current lore:"), lore)));
        loreItem.setItemMeta(loreItemMeta);
        NBTItem nbtLore = new NBTItem(loreItem);
        nbtLore.setBoolean("LoreItem", true);
        loreItem = nbtLore.getItem();
         */

        ItemStack healthItem = new ItemStack(XMaterial.RED_DYE.parseMaterial());
        ItemMeta healthItemMeta = healthItem.getItemMeta();
        healthItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Health"));
        healthItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of health that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + healthAmount))));
        healthItem.setItemMeta(healthItemMeta);
        NBTItem nbtHealth = new NBTItem(healthItem);
        nbtHealth.setBoolean("HealthItem", true);
        healthItem = nbtHealth.getItem();

        ItemStack defenseItem = new ItemStack(XMaterial.IRON_CHESTPLATE.parseMaterial());
        ItemMeta defenseItemMeta = defenseItem.getItemMeta();
        defenseItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Defense"));
        defenseItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of defense that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + defenseAmount))));
        defenseItem.setItemMeta(defenseItemMeta);
        NBTItem nbtDefense = new NBTItem(defenseItem);
        nbtDefense.setBoolean("DefenseItem", true);
        defenseItem = nbtDefense.getItem();

        ItemStack intelligenceItem = new ItemStack(XMaterial.LIGHT_BLUE_DYE.parseMaterial());
        ItemMeta intelligenceItemMeta = intelligenceItem.getItemMeta();
        intelligenceItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Intelligence"));
        intelligenceItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of intelligence that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + intelligenceAmount))));
        intelligenceItem.setItemMeta(intelligenceItemMeta);
        NBTItem nbtIntelligence = new NBTItem(intelligenceItem);
        nbtIntelligence.setBoolean("IntelligenceItem", true);
        intelligenceItem = nbtIntelligence.getItem();

        ItemStack forceItem = new ItemStack(XMaterial.TRIDENT.parseMaterial());
        ItemMeta forceItemMeta = forceItem.getItemMeta();
        forceItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Force"));
        forceItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of force that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + forceAmount))));
        forceItem.setItemMeta(forceItemMeta);
        NBTItem nbtForce = new NBTItem(forceItem);
        nbtForce.setBoolean("ForceItem", true);
        forceItem = nbtForce.getItem();

        ItemStack critDamageItem = new ItemStack(XMaterial.DRAGON_HEAD.parseMaterial());
        ItemMeta critDamageItemMeta = critDamageItem.getItemMeta();
        critDamageItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Critical Damage"));
        critDamageItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of crit damage that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + critDamageAmount))));
        critDamageItem.setItemMeta(critDamageItemMeta);
        NBTItem nbtCritDamage = new NBTItem(critDamageItem);
        nbtCritDamage.setBoolean("CritDamageItem", true);
        critDamageItem = nbtCritDamage.getItem();

        ItemStack luckyChanceItem = new ItemStack(XMaterial.EXPERIENCE_BOTTLE.parseMaterial());
        ItemMeta luckyChanceItemMeta = luckyChanceItem.getItemMeta();
        luckyChanceItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Lucky Chance"));
        luckyChanceItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of lucky chance that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + luckyChanceAmount))));
        luckyChanceItem.setItemMeta(luckyChanceItemMeta);
        NBTItem nbtLuckyChance = new NBTItem(luckyChanceItem);
        nbtLuckyChance.setBoolean("LuckyChanceItem", true);
        luckyChanceItem = nbtLuckyChance.getItem();

        ItemStack speedItem = new ItemStack(XMaterial.SUGAR.parseMaterial());
        ItemMeta speedItemMeta = speedItem.getItemMeta();
        speedItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Speed"));
        speedItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of speed that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + speedAmount))));
        speedItem.setItemMeta(speedItemMeta);
        NBTItem nbtSpeed = new NBTItem(speedItem);
        nbtSpeed.setBoolean("SpeedItem", true);
        speedItem = nbtSpeed.getItem();

        ItemStack agilityItem = new ItemStack(XMaterial.NETHERITE_BOOTS.parseMaterial());
        ItemMeta agilityItemMeta = agilityItem.getItemMeta();
        agilityItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Agility"));
        agilityItemMeta.setLore(ChatColor.colorise(ChatColor.colorise(Arrays.asList("&7Click here to set the amount", "&7of agility that this item will give", "&7the player when held / worn", "", "&7Current amount: &a" + agilityAmount))));
        agilityItem.setItemMeta(agilityItemMeta);
        NBTItem nbtAgility = new NBTItem(agilityItem);
        nbtAgility.setBoolean("AgilityItem", true);
        agilityItem = nbtAgility.getItem();

        ItemStack abilityItem = new ItemStack(XMaterial.BEACON.parseMaterial());
        ItemMeta abilityItemMeta = abilityItem.getItemMeta();
        abilityItemMeta.setDisplayName(ChatColor.colorise("&6&lItem Ability"));
        abilityItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to set the ability of", "&7this item!", "", "&7Current ability: &a" + abilityName)));
        abilityItem.setItemMeta(abilityItemMeta);
        NBTItem nbtAbility = new NBTItem(abilityItem);
        nbtAbility.setBoolean("AbilityItem", true);
        abilityItem = nbtAbility.getItem();

        ItemStack baseDamageItem = new ItemStack(XMaterial.DIAMOND_AXE.parseMaterial());
        ItemMeta baseDamageMeta = baseDamageItem.getItemMeta();
        baseDamageMeta.setDisplayName(ChatColor.colorise("&6&lItem damage"));
        baseDamageMeta.setLore(ChatColor.colorise(Arrays.asList("&7Click here to set the base damage", "&7of this item!", "", "&7Current damage: &a" + builder.getBaseDamage())));
        baseDamageItem.setItemMeta(baseDamageMeta);
        NBTItem nbtBaseDamage = new NBTItem(baseDamageItem);
        nbtBaseDamage.setBoolean("BaseDamageItem", true);
        baseDamageItem = nbtBaseDamage.getItem();


        inv.setItem(10, baseDamageItem);
        inv.setItem(19, rarity);
       // inv.setItem(16, nameItem);
        inv.setItem(25, uses);
        //inv.setItem(25, loreItem);

        inv.setItem(31, agilityItem);
        inv.setItem(37, healthItem);
        inv.setItem(38, defenseItem);
        inv.setItem(39, intelligenceItem);
        inv.setItem(40, forceItem);
        inv.setItem(41, critDamageItem);
        inv.setItem(42, luckyChanceItem);
        inv.setItem(43, speedItem);

        inv.setItem(49, abilityItem);

        inv.setItem(13, builder.getItem());

        return inv;
    }
}
