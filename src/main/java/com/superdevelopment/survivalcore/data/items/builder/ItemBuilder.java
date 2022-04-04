package com.superdevelopment.survivalcore.data.items.builder;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.guis.BuilderGUI;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.data.items.builder.data.Rarity;
import com.superdevelopment.survivalcore.data.items.builder.data.Selected;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTCompoundList;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import de.tr7zw.changeme.nbtapi.NBTType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemBuilder {
    private Player player;
    private ItemStack item;
    private XMaterial material;
    private String name;
    private List<String> lore = Arrays.asList("None!");
    private HashMap<Enchant, Integer> enchants = new HashMap<>();
    private int uses;
    private Rarity rarity = Rarity.COMMON;
    private Selected selected = Selected.NONE;
    private Ability ability;
    private int baseDamage = 10;

    private int health;
    private int defense;
    private int intelligence;
    private int force;
    private int critDamage;
    private int luckyChance;
    private int speed;
    private int agility;

    public ItemBuilder(ItemStack item, Player player) {
        this.player = player;
        this.item = item;
        this.name = item.getItemMeta().getDisplayName();

        NBTItem nbtItem = new NBTItem(item);

        if(nbtItem.hasKey("enchants")) {
            this.enchants = Enchant.getItemEnchants(this.item);
        }

        if (nbtItem.hasKey("Rarity")) {
            this.baseDamage = nbtItem.getInteger("ItemBaseDamage");
            this.rarity = nbtItem.getObject("Rarity", Rarity.class);
            this.lore = nbtItem.getObject("Lore", List.class);
            this.health = nbtItem.getInteger("Health");
            this.defense = nbtItem.getInteger("Defense");
            this.intelligence = nbtItem.getInteger("Intelligence");
            this.force = nbtItem.getInteger("Force");
            this.critDamage = nbtItem.getInteger("CritDamage");
            this.luckyChance = nbtItem.getInteger("LuckyChance");
            this.speed = nbtItem.getInteger("Speed");
            this.agility = nbtItem.getInteger("Agility");
            if(nbtItem.hasKey("Ability")) this.ability = nbtItem.getObject("Ability", Ability.class);
            if(nbtItem.hasKey("MaxUses")) this.uses = nbtItem.getInteger("MaxUses");
        } else {
            this.material = XMaterial.matchXMaterial(item);
            this.lore = item.getItemMeta().getLore();
        }
    }

    public XMaterial getMaterial() {
        return material;
    }
    public String getName() {
        return name;
    }
    public List<String> getLore() {
        return lore;
    }
    public HashMap<Enchant, Integer> getEnchants() {
        return enchants;
    }
    public int getUses() {
        return uses;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public Selected getSelection() {
        return this.selected;
    }
    public int getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
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
    public double getBaseDamage() {
        return baseDamage;
    }
    public Ability getAbility() {
        return this.ability;
    }
    public ItemStack getItem() {
        ItemStack i = this.item;
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        if(this.uses == 0) meta.setUnbreakable(true);
        else meta.setUnbreakable(false);
        i.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(i);

        nbtItem.setObject("Rarity", this.rarity);
        nbtItem.setInteger("ItemBaseDamage", this.baseDamage);
        nbtItem.setObject("Lore", this.lore);
        nbtItem.setObject("enchants", this.enchants);
        nbtItem.setInteger("Health", this.health);
        nbtItem.setInteger("Defense", this.defense);
        nbtItem.setInteger("Intelligence", this.intelligence);
        nbtItem.setInteger("Force", this.force);
        nbtItem.setInteger("CritDamage", this.critDamage);
        nbtItem.setInteger("LuckyChance", this.luckyChance);
        nbtItem.setInteger("Speed", this.speed);
        nbtItem.setInteger("Agility", this.agility);
        if(this.ability != null) {
            nbtItem.setObject("Ability", this.ability);
            if(this.ability == Ability.ENDER_MANIAC) {
                nbtItem.setInteger("endermanKills", 0);
            }
        }
        if(uses != 0) nbtItem.setInteger("Uses", this.uses); nbtItem.setInteger("MaxUses", this.uses);
        i = nbtItem.getItem();

        meta = i.getItemMeta();
        meta.setLore(new LoreBuilder(i).buildLore());
        i.setItemMeta(meta);

        return i;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setItem(ItemStack item) {
        this.item = item;
    }
    public void setMaterial(XMaterial material) {
        this.material = material;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public void setEnchants(HashMap<Enchant, Integer> enchants) {
        this.enchants = enchants;
    }
    public void setUses(int uses) {
        this.uses = uses;
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setForce(int force) {
        this.force = force;
    }
    public void setCritDamage(int critDamage) {
        this.critDamage = critDamage;
    }
    public void setLuckyChance(int luckyChance) {
        this.luckyChance = luckyChance;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    public void setBaseAttackDamage(int amount) {
        this.baseDamage = amount;
    }

    public void setSelection(Selected selected) {
        this.selected = selected;
    }

    public void scrollRarity() {
        this.rarity = rarity.shuffleRarities();
        refreshInventory();
    }
    public void refreshInventory() {
        this.player.openInventory(BuilderGUI.getInventory(this));
    }
}
