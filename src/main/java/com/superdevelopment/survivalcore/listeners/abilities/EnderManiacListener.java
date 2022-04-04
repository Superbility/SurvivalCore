package com.superdevelopment.survivalcore.listeners.abilities;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class EnderManiacListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    private int time = plugin.getConfig().getInt("Abilities.EnderManiac.cooldown");
    private int mana = plugin.getConfig().getInt("Abilities.EnderManiac.mana-cost");

    @EventHandler
    private void onRightClick(PlayerInteractEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
        Player player = e.getPlayer();
        if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(e.getItem());
            if (nbtItem.hasKey("Ability")) {
                Ability ability = nbtItem.getObject("Ability", Ability.class);
                if (ability == Ability.ENDER_MANIAC) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (!hasCooldown(player)) {
                            if (hasIntelligence(sPlayer, player)) {
                                cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (time * 1000));

                                player.sendMessage(ChatColor.GREEN + "Ender-Maniac Activated!");

                                int endermanKills = nbtItem.getInteger("endermanKills");
                                int multiplier = endermanKills / 500;

                                if(multiplier > 0) {
                                    sPlayer.addForce(25 * multiplier);
                                    sPlayer.addAgility(1 * multiplier);

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            sPlayer.removeForce(25 * multiplier);
                                            sPlayer.removeAgility(1 * multiplier);
                                            player.sendMessage(ChatColor.RED + "Ender-Maniac has run out!");
                                        }
                                    }.runTaskLater(plugin, 20 * 120);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    private void onKill(EntityDamageByEntityEvent e) {
        if(e.getEntity().getType() == EntityType.ENDERMAN) {
            if(e.getDamager() instanceof Player) {
                Player player = (Player) e.getDamager();
                if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
                    NBTItem nbtItem = new NBTItem(player.getItemInHand());
                    if (nbtItem.hasKey("Ability")) {
                        Ability ability = nbtItem.getObject("Ability", Ability.class);
                        if (ability == Ability.ENDER_MANIAC) {
                            nbtItem.setInteger("endermanKills", nbtItem.getInteger("endermanKills") + 1);
                            player.setItemInHand(nbtItem.getItem());
                        }
                    }
                }
            }
        }
    }

    private boolean hasCooldown(Player player) {
        if (cooldowns.containsKey(player.getUniqueId())) { //Player in cooldown
            if (cooldowns.get(player.getUniqueId()) > System.currentTimeMillis()) { //Still have cooldown
                player.sendMessage(ChatColor.RED + "This item is currently on cooldown!");
                return true;
            }
        }
        return false;
    }

    private boolean hasIntelligence(SurvivalPlayer sPlayer, Player player) {
        if (sPlayer.getIntelligence() < mana) {
            player.sendMessage(ChatColor.RED + "You don't have enough intelligence for this!");
            return false;
        }
        sPlayer.removeIntelligence(mana);
        return true;
    }
}
