package com.superdevelopment.survivalcore.data.configs;

import com.superdevelopment.survivalcore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class VaultsConfig {
    private Main plugin = Main.getPlugin(Main.class);

    public static File file;
    public static FileConfiguration config;
    public static UUID uuid;

    public VaultsConfig(UUID uuid) {
        this.uuid = uuid;
        setup();
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        File folder = new File(plugin.getDataFolder() + "/Vaults");
        if(!folder.exists()) {
            folder.mkdir();
        }

        file = new File(plugin.getDataFolder() + "/Vaults", uuid + ".yml");
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
        for(int i = 1; i < 51; ++i) {
            config.set("Vaults." + i + ".Items", new ArrayList<HashMap>());
        }
        saveFile();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
        }
    }

    public void reloadFile() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}