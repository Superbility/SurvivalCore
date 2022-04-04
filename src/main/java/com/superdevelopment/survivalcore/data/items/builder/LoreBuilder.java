package com.superdevelopment.survivalcore.data.items.builder;

import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.data.items.builder.data.Rarity;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LoreBuilder {
    private ItemStack item;

    private HashMap<Enchant, Integer> enchants;
    private Rarity rarity;
    private List<String> lore;
    private int health;
    private int defense;
    private int intelligence;
    private int force;
    private int critDamage;
    private int luckyChance;
    private int speed;
    private int agility;
    private Ability ability;
    private int uses;
    private int damage;

    public LoreBuilder(ItemStack item) {
        this.item = item;

        NBTItem nbtItem = new NBTItem(item);

        if(nbtItem.hasKey("enchants")) {
            this.enchants = Enchant.getItemEnchants(this.item);
        }

        if(nbtItem.hasKey("Lore")) {
            this.lore = nbtItem.getObject("Lore", List.class);
        }

        if (nbtItem.hasKey("Rarity")) {
            this.damage = nbtItem.getInteger("ItemBaseDamage");
            this.rarity = nbtItem.getObject("Rarity", Rarity.class);
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
        }
    }

    public List<String> buildLore() {
        List<String> newLore = new ArrayList<>();
        newLore.add(ChatColor.colorise("&7Damage: &c" + (int) damage));
        if(this.health > 0) newLore.add(ChatColor.colorise("&7Health: &c" + health));
        if(this.defense > 0) newLore.add(ChatColor.colorise("&7Defense: &c" + defense));
        if(this.intelligence > 0) newLore.add(ChatColor.colorise("&7Intelligence: &c" + intelligence));
        if(this.force > 0) newLore.add(ChatColor.colorise("&7Force: &c" + force));
        if(this.critDamage > 0) newLore.add(ChatColor.colorise("&7Crit-Damage: &c" + critDamage));
        if(this.luckyChance > 0) newLore.add(ChatColor.colorise("&7Lucky Chance: &c" + luckyChance));
        if(this.speed > 0) newLore.add(ChatColor.colorise("&7Speed: &c" + speed));
        if(this.agility > 0) newLore.add(ChatColor.colorise("&7Agility: &c" + agility));
        if(lore != null && !lore.isEmpty()) {
            newLore.add("");
            for (String s : lore) {
                newLore.add(ChatColor.colorise(s));
            }
        } //Lore
        if(this.ability != null) {
            newLore.add("");
            for(String s : this.ability.getDescription()) {
                newLore.add(s);
            }
        } //Ability

        if(!enchants.isEmpty()) {
            if (enchants.size() > 4) {
                newLore.add("");
                newLore.add(ChatColor.colorise("&7Item Enchants:"));
                AtomicInteger count = new AtomicInteger();
                StringBuilder temp = new StringBuilder();
                enchants.forEach((key, value) -> {

                    //             Replace with num of columns per row
                    //                            ^
                    //                            |
                    if (count.get() != 0 && count.get() % 2 == 0) {
                        temp.append(", ");
                        newLore.add(ChatColor.colorise(temp.toString()));
                        temp.setLength(0);
                    } else if (count.get() != 0) {
                        temp.append(", ");
                    }
                    temp.append("&e").append(key.getName().replace("{tier}", String.valueOf(value)));
                    count.getAndIncrement();
                });
                newLore.add(ChatColor.colorise(temp.toString()));
            } else {
                newLore.add("");
                newLore.add(ChatColor.colorise("&7Item Enchants:"));
                for(Enchant e : enchants.keySet()) {
                    newLore.add(ChatColor.colorise("&e" + e.getName().replace("{tier}", String.valueOf(enchants.get(e)))));
                }
            }
        }

        if(this.uses != 0) {
            newLore.add("");
            newLore.add(ChatColor.colorise("&7Hits remaining: &c" + this.uses + "&7/&c" + this.uses));
        }

        if(this.rarity != null) {
            newLore.add("");
            newLore.add(rarity.getDisplayName() + " item");
        }

        return newLore;
    }
}
