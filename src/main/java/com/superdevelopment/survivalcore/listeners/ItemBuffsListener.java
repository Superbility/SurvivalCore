package com.superdevelopment.survivalcore.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemBuffsListener implements Listener {
    @EventHandler
    private void onEquip(PlayerArmorChangeEvent e) {
        SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());

        if(e.getOldItem() != null && e.getOldItem().getType() != XMaterial.AIR.parseMaterial()) {
            ItemStack oldArmor = e.getOldItem();
            HashMap<Enchant, Integer> enchants = Enchant.getItemEnchants(oldArmor);
            for(Enchant ench : enchants.keySet()) {
                ench.removeStaticEnchants(player, enchants.get(ench));
            }
        }

        if(e.getNewItem() != null && e.getNewItem().getType() != XMaterial.AIR.parseMaterial()) {
            ItemStack newArmor = e.getNewItem();
            HashMap<Enchant, Integer> enchants = Enchant.getItemEnchants(newArmor);
            for(Enchant ench : enchants.keySet()) {
                ench.addStaticEnchants(player, enchants.get(ench));
            }
        }
    }

    @EventHandler
    private void addBuffs(PlayerArmorChangeEvent e) {
        SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());

        if(e.getOldItem() != null && e.getOldItem().getType() != XMaterial.AIR.parseMaterial()) {
            ItemStack oldArmor = e.getOldItem();
            NBTItem nbtItem = new NBTItem(oldArmor);

            if(nbtItem.hasKey("Health")) player.removeMaxHealth(nbtItem.getInteger("Health"));
            if(nbtItem.hasKey("Defense")) player.removeDefense(nbtItem.getInteger("Defense"));
            if(nbtItem.hasKey("Intelligence")) player.removeMaxIntelligence(nbtItem.getInteger("Intelligence"));
            if(nbtItem.hasKey("Force")) player.removeForce(nbtItem.getInteger("Force"));
            if(nbtItem.hasKey("CritDamage")) player.removeCritDamage(nbtItem.getInteger("CritDamage"));
            if(nbtItem.hasKey("LuckyChance")) player.removeLuckyChance(nbtItem.getInteger("LuckyChance"));
            if(nbtItem.hasKey("Speed")) player.removeSpeed(nbtItem.getInteger("Speed"));
            if(nbtItem.hasKey("Agility")) player.removeAgility(nbtItem.getInteger("Agility"));
        }

        if(e.getNewItem() != null && e.getNewItem().getType() != XMaterial.AIR.parseMaterial()) {
            ItemStack newArmor = e.getNewItem();
            NBTItem nbtItem = new NBTItem(newArmor);

            if(nbtItem.hasKey("Health")) player.addMaxHealth(nbtItem.getInteger("Health"));
            if(nbtItem.hasKey("Defense")) player.addDefense(nbtItem.getInteger("Defense"));
            if(nbtItem.hasKey("Intelligence")) player.addMaxIntelligence(nbtItem.getInteger("Intelligence"));
            if(nbtItem.hasKey("Force")) player.addForce(nbtItem.getInteger("Force"));
            if(nbtItem.hasKey("CritDamage")) player.addCritDamage(nbtItem.getInteger("CritDamage"));
            if(nbtItem.hasKey("LuckyChance")) player.addLuckyChance(nbtItem.getInteger("LuckyChance"));
            if(nbtItem.hasKey("Speed")) player.addSpeed(nbtItem.getInteger("Speed"));
            if(nbtItem.hasKey("Agility")) player.addAgility(nbtItem.getInteger("Agility"));
        }
    }

    @EventHandler
    private void switchHand(PlayerItemHeldEvent e) {
        SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());

        ItemStack oldItem = e.getPlayer().getInventory().getItem(e.getPreviousSlot());
        if (oldItem != null && oldItem.getType() != XMaterial.AIR.parseMaterial()) {
            if (ItemType.getType(oldItem) != null && ItemType.getType(oldItem) != ItemType.ARMOR) {
                NBTItem nbtItem = new NBTItem(oldItem);
                if (nbtItem.hasKey("Health")) player.removeMaxHealth(nbtItem.getInteger("Health"));
                if (nbtItem.hasKey("Defense")) player.removeDefense(nbtItem.getInteger("Defense"));
                if (nbtItem.hasKey("Intelligence")) player.removeMaxIntelligence(nbtItem.getInteger("Intelligence"));
                if (nbtItem.hasKey("Force")) player.removeForce(nbtItem.getInteger("Force"));
                if (nbtItem.hasKey("CritDamage")) player.removeCritDamage(nbtItem.getInteger("CritDamage"));
                if (nbtItem.hasKey("LuckyChance")) player.removeLuckyChance(nbtItem.getInteger("LuckyChance"));
                if (nbtItem.hasKey("Speed")) player.removeSpeed(nbtItem.getInteger("Speed"));
                if (nbtItem.hasKey("Agility")) player.removeAgility(nbtItem.getInteger("Agility"));
            }
        }

        ItemStack newItem = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (newItem != null && newItem.getType() != XMaterial.AIR.parseMaterial()) {
            if (ItemType.getType(newItem) != null && ItemType.getType(newItem) != ItemType.ARMOR) {
                NBTItem nbtItem = new NBTItem(newItem);
                if (nbtItem.hasKey("Health")) player.addMaxHealth(nbtItem.getInteger("Health"));
                if (nbtItem.hasKey("Defense")) player.addDefense(nbtItem.getInteger("Defense"));
                if (nbtItem.hasKey("Intelligence")) player.addMaxIntelligence(nbtItem.getInteger("Intelligence"));
                if (nbtItem.hasKey("Force")) player.addForce(nbtItem.getInteger("Force"));
                if (nbtItem.hasKey("CritDamage")) player.addCritDamage(nbtItem.getInteger("CritDamage"));
                if (nbtItem.hasKey("LuckyChance")) player.addLuckyChance(nbtItem.getInteger("LuckyChance"));
                if (nbtItem.hasKey("Speed")) player.addSpeed(nbtItem.getInteger("Speed"));
                if (nbtItem.hasKey("Agility")) player.addAgility(nbtItem.getInteger("Agility"));
            }
        }
    }
}
