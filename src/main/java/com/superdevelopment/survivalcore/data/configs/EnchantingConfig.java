package com.superdevelopment.survivalcore.data.configs;

import com.superdevelopment.survivalcore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class EnchantingConfig {
    private Main plugin = Main.getPlugin(Main.class);

    public static File file;
    public static FileConfiguration config;

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "enchanting.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                addDefaults();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            config = YamlConfiguration.loadConfiguration(file);
        }
    }

    private void addDefaults() {
        config.createSection("LIFE_ENERGY");
        config.set("LIFE_ENERGY.Name", "&eLife Energy {tier}");
        config.set("LIFE_ENERGY.Lore", Arrays.asList("&7Grants +{amount} health", "", "&7Can be applied to armor"));
        config.set("LIFE_ENERGY.GUI.Name", "&eLife Energy");
        config.set("LIFE_ENERGY.GUI.Lore", Arrays.asList("&7Grants health to the worn", "&7item!", "", "&eClick to view tiers"));
        config.set("LIFE_ENERGY.MaxTier", 5);
        config.set("LIFE_ENERGY.Cost.1", 10);
        config.set("LIFE_ENERGY.Cost.2", 19);
        config.set("LIFE_ENERGY.Cost.3", 31);
        config.set("LIFE_ENERGY.Cost.4", 50);
        config.set("LIFE_ENERGY.Cost.5", 62);

        config.set("VAMPIRE.Name", "&eVampire {tier}");
        config.set("VAMPIRE.Lore", Arrays.asList("&7Steals {amount}% of your opponents health", "&7on hit", "", "&7Can be applied to swords"));
        config.set("VAMPIRE.GUI.Name", "&eVampire");
        config.set("VAMPIRE.GUI.Lore", Arrays.asList("&7Steals a percentage of your", "&7opponents health on hit!", "", "&eClick to view tiers"));
        config.set("VAMPIRE.MaxTier", 3);
        config.set("VAMPIRE.Cost.1", 18);
        config.set("VAMPIRE.Cost.2", 34);
        config.set("VAMPIRE.Cost.3", 49);

        config.set("CRITICAL_STRIKE.Name", "&eCritical Strike {tier}");
        config.set("CRITICAL_STRIKE.Lore", Arrays.asList("&7Increases your crit damage by {amount}%", "", "&7Can be applied to swords"));
        config.set("CRITICAL_STRIKE.GUI.Name", "&eCritical Strike");
        config.set("CRITICAL_STRIKE.GUI.Lore", Arrays.asList("&7Increases your crit damage!", "", "&eClick to view tiers"));
        config.set("CRITICAL_STRIKE.MaxTier", 8);
        config.set("CRITICAL_STRIKE.Cost.1", 8);
        config.set("CRITICAL_STRIKE.Cost.2", 13);
        config.set("CRITICAL_STRIKE.Cost.3", 20);
        config.set("CRITICAL_STRIKE.Cost.4", 31);
        config.set("CRITICAL_STRIKE.Cost.5", 48);
        config.set("CRITICAL_STRIKE.Cost.6", 62);
        config.set("CRITICAL_STRIKE.Cost.7", 87);
        config.set("CRITICAL_STRIKE.Cost.8", 102);

        config.set("END_STRIKE.Name", "&eEnd Strike {tier}");
        config.set("END_STRIKE.Lore", Arrays.asList("&7Increases your damage to all end", "&7mobs by {amount}%", "", "&7Can be applied to swords"));
        config.set("END_STRIKE.GUI.Name", "&eEnd Strike");
        config.set("END_STRIKE.GUI.Lore", Arrays.asList("&7Increases your damage to all", "&7end mobs!", "", "&eClick to view tiers"));
        config.set("END_STRIKE.MaxTier", 7);
        config.set("END_STRIKE.Cost.1", 11);
        config.set("END_STRIKE.Cost.2", 20);
        config.set("END_STRIKE.Cost.3", 33);
        config.set("END_STRIKE.Cost.4", 40);
        config.set("END_STRIKE.Cost.5", 59);
        config.set("END_STRIKE.Cost.6", 82);
        config.set("END_STRIKE.Cost.7", 114);

        config.set("RAW_STRENGTH.Name", "&eRaw Strength {tier}");
        config.set("RAW_STRENGTH.Lore", Arrays.asList("&7Increases base damage of weapon by {amount}", "", "&7Can be applied to swords"));
        config.set("RAW_STRENGTH.GUI.Name", "&eRaw Strength");
        config.set("RAW_STRENGTH.GUI.Lore", Arrays.asList("&7Increases base damage of", "&7the weapon", "", "&eClick to view tiers"));
        config.set("RAW_STRENGTH.MaxTier", 5);
        config.set("RAW_STRENGTH.Cost.1", 30);
        config.set("RAW_STRENGTH.Cost.2", 68);
        config.set("RAW_STRENGTH.Cost.3", 126);
        config.set("RAW_STRENGTH.Cost.4", 143);
        config.set("RAW_STRENGTH.Cost.5", 207);
        
        config.set("WITHER_GUARD.Name", "&eWither Guard {tier}");
        config.set("WITHER_GUARD.Lore", Arrays.asList("&7Reduces the damage taken", "&7by withers or wither skeletons", "&7by {amount}%", "", "&7Can be applied to armor"));
        config.set("WITHER_GUARD.GUI.Name", "&eWither Guard");
        config.set("WITHER_GUARD.GUI.Lore", Arrays.asList("&7Grants health when applied", "", "&eClick to view tiers"));
        config.set("WITHER_GUARD.MaxTier", 5);
        config.set("WITHER_GUARD.Cost.1", 16);
        config.set("WITHER_GUARD.Cost.2", 29);
        config.set("WITHER_GUARD.Cost.3", 41);
        config.set("WITHER_GUARD.Cost.4", 59);
        config.set("WITHER_GUARD.Cost.5", 72);

        config.set("PROTECTION.Name", "&eProtection {tier}");
        config.set("PROTECTION.Lore", Arrays.asList("&7Grants +{defense} when applied", "", "&7Can be applied to armor"));
        config.set("PROTECTION.GUI.Name", "&eProtection");
        config.set("PROTECTION.GUI.Lore", Arrays.asList("&7Grants defense when applied", "", "&eClick to view tiers"));
        config.set("PROTECTION.MaxTier", 6);
        config.set("PROTECTION.Cost.1", 11);
        config.set("PROTECTION.Cost.2", 19);
        config.set("PROTECTION.Cost.3", 27);
        config.set("PROTECTION.Cost.4", 41);
        config.set("PROTECTION.Cost.5", 50);
        config.set("PROTECTION.Cost.6", 62);

        config.set("SHARPNESS.Name", "&eSharpness {tier}");
        config.set("SHARPNESS.Lore", Arrays.asList("&7Deals an additional {amount}%", "&7damage against all mobs!", "", "&7Can be applied to swords"));
        config.set("SHARPNESS.GUI.Name", "&eSharpness");
        config.set("SHARPNESS.GUI.Lore", Arrays.asList("&7Deals additional", "&7damage against all mobs!", "", "&7Can be applied to swords"));
        config.set("SHARPNESS.MaxTier", 7);
        config.set("SHARPNESS.Cost.1", 8);
        config.set("SHARPNESS.Cost.2", 14);
        config.set("SHARPNESS.Cost.3", 22);
        config.set("SHARPNESS.Cost.4", 29);
        config.set("SHARPNESS.Cost.5", 38);
        config.set("SHARPNESS.Cost.6", 65);

        config.set("SMITE.Name", "&eSmite {tier}");
        config.set("SMITE.Lore", Arrays.asList("&7Deals an additional {amount}%", "&7damage against Zombies, Skeletons", "&7Wither Skeletons and Withers", "", "&7Can be applied to swords"));
        config.set("SMITE.GUI.Name", "&eSMITE");
        config.set("SMITE.GUI.Lore", Arrays.asList("&7Deals additional", "&7damage against Zombies, Skeletons", "&7Wither Skeletons and Withers", "", "&7Can be applied to swords"));
        config.set("SMITE.MaxTier", 7);
        config.set("SMITE.Cost.1", 6);
        config.set("SMITE.Cost.2", 13);
        config.set("SMITE.Cost.3", 20);
        config.set("SMITE.Cost.4", 31);
        config.set("SMITE.Cost.5", 58);
        config.set("SMITE.Cost.6", 81);

        config.set("BOA.Name", "&eBane of Arthropods {tier}");
        config.set("BOA.Lore", Arrays.asList("&7Deals an additional {amount}%", "&7damage against Spiders and", "&7Cave Spiders", "", "&7Can be applied to swords"));
        config.set("BOA.GUI.Name", "&eBOA");
        config.set("BOA.GUI.Lore", Arrays.asList("&7Deals additional", "&7damage against Spiders amd", "&7Cave Spiders", "", "&7Can be applied to swords"));
        config.set("BOA.MaxTier", 7);
        config.set("BOA.Cost.1", 6);
        config.set("BOA.Cost.2", 13);
        config.set("BOA.Cost.3", 20);
        config.set("BOA.Cost.4", 31);
        config.set("BOA.Cost.5", 58);
        config.set("BOA.Cost.6", 81);
        
        saveFile();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public static void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
        }
    }

    public void reloadFile() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}