package com.superdevelopment.survivalcore.data;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.configs.DataConfig;
import com.superdevelopment.survivalcore.data.configs.VaultsConfig;
import com.superdevelopment.survivalcore.data.guis.ModeSelectionGUI;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.Anvil;
import com.superdevelopment.survivalcore.data.items.builder.EnchantmentTable;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SurvivalPlayer {
    private Main plugin = Main.getPlugin(Main.class);
    private DataConfig dataConfig = new DataConfig();

    Player player;
    VaultsConfig vaultConfig;
    EnchantmentTable eTable;
    Anvil anvil;
    int maxHealth;
    int health;
    int defense;
    int intelligence;
    int maxIntelligence;
    int force;
    int critDamage;
    int luckyChance;
    int speed;
    int agility;
    boolean adventureMode;

    public SurvivalPlayer(Player player) {
        this.player = player;
        this.vaultConfig = new VaultsConfig(player.getUniqueId());

        recalculateStats();

        addAdventureTags();
    }

    public void resetStats() {
        if(dataConfig.getConfig().contains(this.player.getUniqueId() + ".Stats")) {
            this.maxHealth = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.MaxHealth");
            this.health = maxHealth;
            this.defense = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.Defense");
            this.maxIntelligence = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.Intelligence");
            this.intelligence = maxIntelligence;
            this.force = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.Force");
            this.critDamage = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.CritDamage");
            this.luckyChance = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.LuckyChance");
            this.speed = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.Speed");
            this.agility = dataConfig.getConfig().getInt(this.player.getUniqueId() + ".Stats.Agility");
        } else {
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.MaxHealth", 100);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.Defense", 0);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.Intelligence", 50);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.Force", 10);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.CritDamage", 25);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.LuckyChance", 25);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.Speed", 0);
            dataConfig.getConfig().set(this.player.getUniqueId() + ".Stats.Agility", 0);
            dataConfig.saveFile();

            this.maxHealth = 100;
            this.health = maxHealth;
            this.defense = 0;
            this.maxIntelligence = 50;
            this.intelligence = maxIntelligence;
            this.force = 10;
            this.critDamage = 25;
            this.luckyChance = 25;
            this.speed = 0;
            this.agility = 0;
        }

        if(dataConfig.getConfig().contains(player.getUniqueId() + ".AdventureMode")) {
            this.adventureMode = dataConfig.getConfig().getBoolean(player.getUniqueId() + ".AdventureMode");
        }

        if(this.maxHealth > 10000) this.maxHealth = 10000;
        if(this.luckyChance > 100) this.luckyChance = 100;
        if(this.speed > 200) this.speed = 200;
        if(this.speed < 0) this.speed = 0;
        if(this.agility > 100) this.agility = 100;

        applySpeed();

        this.player.setMaxHealth(this.maxHealth);
        if(this.maxHealth >= 1100) {
            this.player.setHealthScale(40);
        } else {
            this.player.setHealthScale(20 + (this.maxHealth / 50));
        }
    }

    public void recalculateStats() {
        resetStats();

        ItemStack item = player.getItemInHand();
        if (item != null && item.getType() != XMaterial.AIR.parseMaterial()) {
            if (ItemType.getType(item) != null && ItemType.getType(item) != ItemType.ARMOR) {
                NBTItem nbtItem = new NBTItem(item);
                if (nbtItem.hasKey("Health")) addMaxHealth(nbtItem.getInteger("Health"));
                if (nbtItem.hasKey("Defense")) addDefense(nbtItem.getInteger("Defense"));
                if (nbtItem.hasKey("Intelligence")) addMaxIntelligence(nbtItem.getInteger("Intelligence"));
                if (nbtItem.hasKey("Force")) addForce(nbtItem.getInteger("Force"));
                if (nbtItem.hasKey("CritDamage")) addCritDamage(nbtItem.getInteger("CritDamage"));
                if (nbtItem.hasKey("LuckyChance")) addLuckyChance(nbtItem.getInteger("LuckyChance"));
                if (nbtItem.hasKey("Speed")) addSpeed(nbtItem.getInteger("Speed"));
                if (nbtItem.hasKey("Agility")) addAgility(nbtItem.getInteger("Agility"));
            }
        }

        ItemStack[] gear = player.getEquipment().getArmorContents();
        for(ItemStack i : gear) {
            if (i != null && i.getType() != XMaterial.AIR.parseMaterial()) {
                NBTItem nbtItem = new NBTItem(i);
                if (nbtItem.hasKey("Health")) addMaxHealth(nbtItem.getInteger("Health"));
                if (nbtItem.hasKey("Defense")) addDefense(nbtItem.getInteger("Defense"));
                if (nbtItem.hasKey("Intelligence")) addMaxIntelligence(nbtItem.getInteger("Intelligence"));
                if (nbtItem.hasKey("Force")) addForce(nbtItem.getInteger("Force"));
                if (nbtItem.hasKey("CritDamage")) addCritDamage(nbtItem.getInteger("CritDamage"));
                if (nbtItem.hasKey("LuckyChance")) addLuckyChance(nbtItem.getInteger("LuckyChance"));
                if (nbtItem.hasKey("Speed")) addSpeed(nbtItem.getInteger("Speed"));
                if (nbtItem.hasKey("Agility")) addAgility(nbtItem.getInteger("Agility"));
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
    public VaultsConfig getVaultConfig() {
        return vaultConfig;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
    public int getMaxIntelligence() {
        return maxIntelligence;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public int getForce() {
        return force;
    }
    public int getCritDamage() {
        return critDamage;
    }
    public int getLuckyChance() {
        return luckyChance;
    }
    public int getSpeed() {
        return speed;
    }
    public int getAgility() {
        return agility;
    }

    public EnchantmentTable getEnchantmentTable() {
        return this.eTable;
    }
    public void setEnchantmentTable(EnchantmentTable table) {
        this.eTable = table;
    }
    public void closeEnchantmentTable() {
        player.getInventory().addItem(this.eTable.getItem());
        this.eTable = null;
        if(player.getOpenInventory() != null) player.closeInventory();
    }

    public Anvil getAnvil() {
        return this.anvil;
    }
    public void setAnvil(Anvil anvil) {
        this.anvil = anvil;
    }
    public void closeAnvil() {
        if(this.anvil.getLeftItem() != null) player.getInventory().addItem(this.anvil.getLeftItem());
        if(this.anvil.getRightItem() != null) player.getInventory().addItem(this.anvil.getRightItem());
        if(this.anvil.getFinalItem() != null) player.getInventory().addItem(this.anvil.getFinalItem());
        this.anvil = null;
        if(player.getOpenInventory() != null) player.closeInventory();
    }

    public void addDefense(int amount) {
        this.defense = defense + amount;
    }
    public void removeDefense(int amount) {
        this.defense = defense - amount;
    }

    public void addForce(int amount) {
        this.force = this.force + amount;
    }
    public void removeForce(int amount) {
        this.force = this.force - amount;
    }

    public void setIntelligence(int amount) {
        this.intelligence = amount;
    }
    public void removeIntelligence(int amount) {
        this.intelligence = this.intelligence - amount;
    }

    public void addMaxIntelligence(int amount) {
        this.maxIntelligence = this.maxIntelligence + amount;
    }
    public void removeMaxIntelligence(int amount) {
        this.maxIntelligence = this.maxIntelligence - amount;
    }

    public void addAgility(int amount) {
        this.agility = this.agility + amount;
    }
    public void removeAgility(int amount) {
        this.agility = this.agility - amount;
    }

    public void addMaxHealth(int amount) {
        if(this.maxHealth + amount > 10000) this.maxHealth = 10000;
        else this.maxHealth =  this.maxHealth + amount;
        this.player.setMaxHealth(this.maxHealth);
    }
    public void removeMaxHealth(int amount) {
        if(this.maxHealth - amount > 0) {
            this.maxHealth = this.maxHealth - amount;
            this.player.setMaxHealth(this.maxHealth);
        }
    }

    public void addCritDamage(int amount) {
        this.critDamage = this.critDamage + amount;
    }
    public void removeCritDamage(int amount) {
        this.critDamage = this.critDamage - amount;
    }

    public void addLuckyChance(int amount) {
        this.luckyChance = this.luckyChance + amount;
    }
    public void removeLuckyChance(int amount) {
        this.luckyChance = this.luckyChance - amount;
    }

    public void addSpeed(int amount) {
        this.speed = this.speed + amount;
        applySpeed();
    }
    public void removeSpeed(int amount) {
        this.speed = this.speed + amount;
        applySpeed();
    }
    private void applySpeed() {
        if(speed < 40) this.player.setWalkSpeed(0.2f);
        else this.player.setWalkSpeed((float) speed / (200));
    }


    public void damagePlayer(int amount, Entity source) {
        if(getDamageReduction() == 0) {
            this.player.damage(amount);
        } else {
            this.player.damage(Double.valueOf(amount * getDamageReduction()));
        }
    }
    public double getDamageReduction() {
        return Math.round((this.defense / (this.defense + 30)) * 100);
    }
    public int getDamageReductionPercentage() {
        return this.defense / (this.defense + 30) * 100;
    }

    public void setAdventure(boolean adventure) {
        this.adventureMode = adventure;
        dataConfig.getConfig().set(player.getUniqueId() + ".AdventureMode", adventure);
        dataConfig.saveFile();
        addAdventureTags();
    }
    public boolean getAdventureMode() {
        return this.adventureMode;
    }
    private void addAdventureTags() {
        if(this.adventureMode) {
            player.setPlayerListName(ChatColor.colorise("&8[&a✹&8] &r" + player.getDisplayName()));
            player.setDisplayName(ChatColor.colorise("&8[&a✹&8] &r" + player.getDisplayName()));
        }
    }


}
