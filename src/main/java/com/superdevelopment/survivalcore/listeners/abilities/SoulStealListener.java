package com.superdevelopment.survivalcore.listeners.abilities;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class SoulStealListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private HashMap<UUID, Integer> forceAmount = new HashMap<>();
    private HashMap<UUID, Long> abilityTime = new HashMap<>();

    private int time = plugin.getConfig().getInt("Abilities.SoulSteal.cooldown");
    private int mana = plugin.getConfig().getInt("Abilities.SoulSteal.mana-cost");

    @EventHandler
    private void onRightClick(PlayerInteractEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
        Player player = e.getPlayer();
        if(e.getItem() != null && e.getItem().getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(e.getItem());
            if(nbtItem.hasKey("Ability")) {
                Ability ability = nbtItem.getObject("Ability", Ability.class);
                if(ability == Ability.SOUL_STEAL) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if(!hasCooldown(player)) {
                            if (hasIntelligence(sPlayer, player)) {
                                cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (time * 1000));

                                startAbility(sPlayer);
                                forceAmount.put(player.getUniqueId(), 5);
                                abilityTime.put(player.getUniqueId(), System.currentTimeMillis() + 10000);
                                player.sendMessage(ChatColor.GREEN + "Soul-Steal activated!");
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    private void onKill(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) e.getEntity();
            if(entity.getHealth() - e.getDamage() <= 0) {
                if (e.getDamager() instanceof Player) {
                    Player player = (Player) e.getDamager();
                    SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(player);
                    if(forceAmount.containsKey(player.getUniqueId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        int currentAmount = forceAmount.get(player.getUniqueId());
                        if(currentAmount + 5 <= 300) {
                            forceAmount.replace(player.getUniqueId(), currentAmount + 5);
                            sPlayer.addForce(5);
                        }
                        abilityTime.replace(player.getUniqueId(), System.currentTimeMillis() + 10000);
                    }
                }
            }
        }
    }
    private void startAbility(SurvivalPlayer sPlayer) {
        Player player = sPlayer.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (abilityTime.get(player.getUniqueId()) <= System.currentTimeMillis()) {
                    cancel();

                    player.sendMessage(ChatColor.RED + "Your Soul-Steal ability has run out!");
                    sPlayer.removeForce(forceAmount.get(player.getUniqueId()));

                    forceAmount.remove(player.getUniqueId());
                    abilityTime.remove(player.getUniqueId());
                    cooldowns.remove(player.getUniqueId());
                }
            }
        }.runTaskTimer(plugin, 5, 5);
    }
    private boolean hasCooldown(Player player) {
        if(cooldowns.containsKey(player.getUniqueId())) { //Player in cooldown
            if(cooldowns.get(player.getUniqueId()) > System.currentTimeMillis()) { //Still have cooldown
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
