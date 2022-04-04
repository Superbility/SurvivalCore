package com.superdevelopment.survivalcore.data.configs;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MenuConfig {
    private Main plugin = Main.getPlugin(Main.class);

    public static File file;
    public static FileConfiguration config;

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "Menu.yml");
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
        config.createSection("Item");
        config.set("Item.Material", "COMPASS");
        config.set("Item.Name", "&b&lSurvival Menu");
        config.set("Item.Lore", Arrays.asList("&7Click here to open the", "&7survival menu!"));

        config.set("Menus.MainMenu.Name", "&7Main Menu");
        config.set("Menus.MainMenu.Size", 36);

        config.set("Menus.MainMenu.Slot.13.ClickedLocation", "STATS");
        config.set("Menus.MainMenu.Slot.13.Material", "ITEM_FRAME");
        config.set("Menus.MainMenu.Slot.13.Name", "&6&lYour Stats");
        config.set("Menus.MainMenu.Slot.13.Lore", Arrays.asList("&7Each of your stats plays a", "&7role in your progress towards", "&7fully completing the game!", "", "&eClick to view your stats!"));

        config.set("Menus.MainMenu.Slot.21.ClickedLocation", "VAULTS");
        config.set("Menus.MainMenu.Slot.21.Material", "CHEST");
        config.set("Menus.MainMenu.Slot.21.Name", "&6&lVaults");
        config.set("Menus.MainMenu.Slot.21.Lore", Arrays.asList("&7Store your items in these super", "&7safe vaults!", " ", "&eClick to open your vaults!"));

        config.set("Menus.MainMenu.Slot.22.ClickedLocation", "LEVELS");
        config.set("Menus.MainMenu.Slot.22.Material", "EXPERIENCE_BOTTLE");
        config.set("Menus.MainMenu.Slot.22.Name", "&6&lLevels");
        config.set("Menus.MainMenu.Slot.22.Lore", Arrays.asList("&7As you level up, you will", "&7gain additional stats and", "&7unlock new features/items!", "", "&eClick to view your level progression!"));



        config.set("Menus.Stats.Name", "Stats Menu");
        config.set("Menus.Stats.Size", 36);

        config.set("Menus.Stats.Slot.11.Material", "RED_DYE");
        config.set("Menus.Stats.Slot.11.Name", "&c&lHealth");
        config.set("Menus.Stats.Slot.11.Lore", Arrays.asList("&7Having more health increases your", "&7maximum health and number of hearts", "", "&eYour max health: &c{maximum_health}", "", "&7Increase health by leveling up", "&7finding reaper fragments, or acquiring", "&7god upgrades!")); //TODO PLACEHOLDER: {maximum_health} returns the players max health

        config.set("Menus.Stats.Slot.12.Material", "SLIME_BALL");
        config.set("Menus.Stats.Slot.12.Name", "&2&lDefense");
        config.set("Menus.Stats.Slot.12.Lore", Arrays.asList("&7Having more defense reduces the", "&7damage taken from enemies", "", "&eYour defense: &c{defense}","&eYour damage reduction: &c{damage_reduction}", "", "&7Increase defense by leveling up", "&7finding reaper fragments, acquiring", "&7god upgrades, or sturdying yourself!")); //TODO PLACEHOLDER: {defense} returns the players defense

        config.set("Menus.Stats.Slot.13.Material", "REDSTONE");
        config.set("Menus.Stats.Slot.13.Name", "&4&lForce");
        config.set("Menus.Stats.Slot.13.Lore", Arrays.asList("&7Force increase the damage output", "&7of all hits with any weapon", "", "&eYour force: &c{force}", "", "&7Increase your force by finding reaper", "&7fragments, purchasing god upgrades and", "&7leveling up."));

        config.set("Menus.Stats.Slot.14.Material", "SUGAR");
        config.set("Menus.Stats.Slot.14.Name", "&f&lSpeed");
        config.set("Menus.Stats.Slot.14.Lore", Arrays.asList("&7Having more speed increases your", "&7walk speed", "", "&eYour speed: &c{speed}", "", "&7Increase speed by leveling up", "&7finding reaper fragments, or acquiring", "&7god upgrades!")); //TODO PLACEHOLDER: {speed} returns the players speed

        config.set("Menus.Stats.Slot.15.Material", "BLAZE_POWDER");
        config.set("Menus.Stats.Slot.15.Name", "&3&lLucky Chance");
        config.set("Menus.Stats.Slot.15.Lore", Arrays.asList("&7Lucky Chance increases the chance for you to", "&7land a critical hit, dealing a lot of extra damage.", "", "&eYour Lucky Chance: &c{luckychance}", "", "&7Increase your Lucky Chance by purchasing god", "&7upgrades and leveling up"));

        config.set("Menus.Stats.Slot.22.Material", "WITHER_SKELETON_SKULL");
        config.set("Menus.Stats.Slot.22.Name", "&5&lCritical Damage Boost");
        config.set("Menus.Stats.Slot.22.Lore", Arrays.asList("&7Critical Damage Boost determines how much more", "&7damage is dealt on critical hits.", "", "&aYour Critical Damage Boost: &c{critdamage}%", "", "&7Increase Critical Damage Boost by purchasing god", "&7upgrades and leveling up"));

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