package com.superdevelopment.survivalcore;

import com.superdevelopment.survivalcore.commands.AttributeCommand;
import com.superdevelopment.survivalcore.commands.GiveEnchantCommand;
import com.superdevelopment.survivalcore.commands.ItemBuilderCommand;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.DataConfig;
import com.superdevelopment.survivalcore.data.configs.EnchantingConfig;
import com.superdevelopment.survivalcore.data.configs.MenuConfig;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.listeners.*;
import com.superdevelopment.survivalcore.listeners.abilities.*;
import com.superdevelopment.survivalcore.listeners.anvil.AnvilClickListener;
import com.superdevelopment.survivalcore.listeners.anvil.AnvilInvClickListener;
import com.superdevelopment.survivalcore.listeners.anvil.AnvilInvCloseListener;
import com.superdevelopment.survivalcore.listeners.enchanting.EnchantTableClickListener;
import com.superdevelopment.survivalcore.listeners.enchanting.EnchantingInvClickListener;
import com.superdevelopment.survivalcore.listeners.enchanting.EnchantingInvCloseListener;
import com.superdevelopment.survivalcore.listeners.inventorylisteners.StatsMenuClickListener;
import com.superdevelopment.survivalcore.listeners.inventorylisteners.SurvivalMenuClickListener;
import com.superdevelopment.survivalcore.listeners.inventorylisteners.vault.VaultClickListener;
import com.superdevelopment.survivalcore.listeners.inventorylisteners.vault.VaultCloseListener;
import com.superdevelopment.survivalcore.listeners.itembuilder.AbilityClickListener;
import com.superdevelopment.survivalcore.listeners.itembuilder.ItemBuilderClickListener;
import com.superdevelopment.survivalcore.listeners.itembuilder.ItemBuilderCloseInventoryListener;
import com.superdevelopment.survivalcore.listeners.itembuilder.PlayerChatListener;
import com.superdevelopment.survivalcore.listeners.modeselection.ModeSelectClickListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {
    public HashMap<UUID, SurvivalPlayer> survivalPlayers = new HashMap<>();
    public HashMap<UUID, ItemBuilder> itemBuildingPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new SurvivalMenuClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamagedListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerHealListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new HitMarkerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new ItemBuilderClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new ItemBuilderCloseInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageItemListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityClickListener(), this);
        getServer().getPluginManager().registerEvents(new VaultClickListener(), this);
        getServer().getPluginManager().registerEvents(new VaultCloseListener(), this);
        getServer().getPluginManager().registerEvents(new StatsMenuClickListener(), this);

        getServer().getPluginManager().registerEvents(new TeleportListener(), this);
        getServer().getPluginManager().registerEvents(new SoulStealListener(), this);
        getServer().getPluginManager().registerEvents(new InstantFireListener(), this);
        getServer().getPluginManager().registerEvents(new RapidHealListener(), this);
        getServer().getPluginManager().registerEvents(new MagmaMountainListener(), this);
        getServer().getPluginManager().registerEvents(new BulkUpListener(), this);
        getServer().getPluginManager().registerEvents(new AssassinStrikeListener(), this);
        getServer().getPluginManager().registerEvents(new SpiritWaveAbility(), this);
        getServer().getPluginManager().registerEvents(new WitherGlideListener(), this);
        getServer().getPluginManager().registerEvents(new EnderManiacListener(), this);

        getServer().getPluginManager().registerEvents(new EnchantTableClickListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantingInvClickListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantingInvCloseListener(), this);

        getServer().getPluginManager().registerEvents(new AnvilClickListener(), this);
        getServer().getPluginManager().registerEvents(new AnvilInvClickListener(), this);
        getServer().getPluginManager().registerEvents(new AnvilInvCloseListener(), this);

        getServer().getPluginManager().registerEvents(new ItemBuffsListener(), this);

        getServer().getPluginManager().registerEvents(new ModeSelectClickListener(), this);
        getServer().getPluginManager().registerEvents(new CompassInteractListener(), this);

        getCommand("attribute").setExecutor(new AttributeCommand());
        getCommand("itembuilder").setExecutor(new ItemBuilderCommand());
        getCommand("genchant").setExecutor(new GiveEnchantCommand());

        saveConfigs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void saveConfigs() {
        MenuConfig menuConfig = new MenuConfig();
        menuConfig.setup();

        DataConfig dataConfig = new DataConfig();
        dataConfig.setup();

        EnchantingConfig enchConfig = new EnchantingConfig();
        enchConfig.setup();

        saveDefaultConfig();
    }
}
