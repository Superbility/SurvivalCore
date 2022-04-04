package com.superdevelopment.survivalcore.listeners.itembuilder;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import com.superdevelopment.survivalcore.data.items.builder.data.Selected;
import com.superdevelopment.survivalcore.utils.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        if(plugin.itemBuildingPlayers.containsKey(player.getUniqueId())) {
            ItemBuilder builder = plugin.itemBuildingPlayers.get(player.getUniqueId());
            if(builder.getSelection().equals(Selected.NAME)) {
                builder.setName(ChatColor.colorise(e.getMessage()));
                builder.refreshInventory();
            } else if(builder.getSelection().equals(Selected.USES)) {
                builder.setUses(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.HEALTH)) {
                builder.setHealth(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.DEFENSE)) {
                builder.setDefense(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.INTELLIGENCE)) {
                builder.setIntelligence(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.FORCE)) {
                builder.setForce(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.CRITDAMAGE)) {
                builder.setCritDamage(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.LUCKYCHANCE)) {
                builder.setLuckyChance(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.SPEED)) {
                builder.setSpeed(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.AGILITY)) {
                builder.setAgility(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            } else if(builder.getSelection().equals(Selected.BASEDAMAGE)) {
                builder.setBaseAttackDamage(Integer.valueOf(e.getMessage()));
                builder.refreshInventory();
                builder.setSelection(Selected.NONE);
                e.setCancelled(true);
            }
        }
    }
}
