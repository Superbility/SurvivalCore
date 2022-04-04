package com.superdevelopment.survivalcore.listeners.abilities;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AssassinStrikeListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    private int time = plugin.getConfig().getInt("Abilities.AssassinStrike.cooldown");
    private int mana = plugin.getConfig().getInt("Abilities.AssassinStrike.mana-cost");

    @EventHandler
    private void onRightClick(PlayerInteractEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
        Player player = e.getPlayer();
        if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(e.getItem());
            if (nbtItem.hasKey("Ability")) {
                Ability ability = nbtItem.getObject("Ability", Ability.class);
                if (ability == Ability.ASSASSIN_STRIKE) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (!hasCooldown(player)) {

                            boolean isValid = false;
                            List<Entity> entities = player.getNearbyEntities(10, 10, 10);
                            for(Entity entity : entities) {
                                if(entity instanceof LivingEntity) {
                                    isValid = true;
                                    break;
                                }
                            }
                            if(!isValid) {
                                player.sendMessage(ChatColor.RED + "No enemy found!");
                                return;
                            }

                            if (hasIntelligence(sPlayer, player)) {
                                cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (time * 1000));

                                player.sendMessage(ChatColor.GREEN + "Assassin-Strike activated!");

                                LivingEntity entity = null;

                                for(Entity ent : entities) {
                                    if(ent instanceof LivingEntity) {
                                        if(!(ent instanceof Player)) {
                                            if (entity == null) {
                                                entity = (LivingEntity) ent;
                                            }

                                            if (player.getLocation().distance(entity.getLocation()) >= player.getLocation().distance(ent.getLocation())) {
                                                entity = (LivingEntity) ent;
                                            }
                                        }
                                    }
                                }

                                entity.setAI(false);
                                player.teleport(entity.getLocation());

                                LivingEntity finalEntity = entity;
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        finalEntity.setAI(true);
                                    }
                                }.runTaskLater(plugin, 60);
                            }
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
