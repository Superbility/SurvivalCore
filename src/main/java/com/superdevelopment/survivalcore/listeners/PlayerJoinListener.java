package com.superdevelopment.survivalcore.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.VaultsConfig;
import com.superdevelopment.survivalcore.data.items.MenuItem;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.utils.Actionbar;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler //Give menu item
    private void giveMenu(PlayerJoinEvent e) {
        ItemStack menuItem = MenuItem.getMenuItem();
        e.getPlayer().getInventory().setItem(8, menuItem);
    }

    @EventHandler
    private void createSurvivalPlayer(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        SurvivalPlayer survivalPlayer = new SurvivalPlayer(player);
        plugin.survivalPlayers.put(player.getUniqueId(), survivalPlayer);

        startActionbar(player, survivalPlayer);
        startManaRegen(survivalPlayer);
    }

    private void startManaRegen(SurvivalPlayer player) {
        double amount = plugin.getConfig().getDouble("Intelligence.Regen.Amount");
        int interval = plugin.getConfig().getInt("Intelligence.Regen.Interval");

        new BukkitRunnable() {
            @Override
            public void run() {
                if(player.getPlayer().isOnline()) {
                    int intelligence = player.getIntelligence();
                    int maxIntelligence = player.getMaxIntelligence();

                    if (intelligence * amount > maxIntelligence) {
                        player.setIntelligence(maxIntelligence);
                    } else {
                        player.setIntelligence((int) (maxIntelligence * amount));
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, interval, interval);
    }
    private void startActionbar(Player player, SurvivalPlayer survivalPlayer) {
        int interval = plugin.getConfig().getInt("Actionbar.Interval");
        String message = plugin.getConfig().getString("Actionbar.Message");
        new BukkitRunnable() {
            @Override
            public void run() {
                if(player.isOnline()) {
                    Actionbar.sendActionText(player, message
                            .replace("{health}", String.valueOf((int) player.getHealth()))
                            .replace("{max_health}", String.valueOf(player.getMaxHealth()))
                            .replace("{mana}", String.valueOf(survivalPlayer.getIntelligence()))
                            .replace("{max_mana}", String.valueOf(survivalPlayer.getMaxIntelligence())));
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, interval, interval);
    }
}
