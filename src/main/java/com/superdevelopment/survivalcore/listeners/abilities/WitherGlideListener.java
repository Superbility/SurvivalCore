package com.superdevelopment.survivalcore.listeners.abilities;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.HashMap;
import java.util.UUID;

public class WitherGlideListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    private int time = plugin.getConfig().getInt("Abilities.WitherGlide.cooldown");
    private int mana = plugin.getConfig().getInt("Abilities.WitherGlide.mana-cost");

    @EventHandler
    private void onRightClick(PlayerInteractEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
        Player player = e.getPlayer();
        if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(e.getItem());
            if (nbtItem.hasKey("Ability")) {
                Ability ability = nbtItem.getObject("Ability", Ability.class);
                if (ability == Ability.WITHER_GLIDE) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (!hasCooldown(player)) {
                            if (hasIntelligence(sPlayer, player)) {
                                cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (time * 1000));

                                Wither wither = (Wither) player.getWorld().spawnEntity(player.getLocation().clone().add(0, 2, 0), EntityType.WITHER);
                                //wither.setAI(false);
                                wither.setGliding(true);
                                wither.setAware(false);
                                wither.setGlowing(true);
                                wither.setPassenger(player);
                                wither.setMetadata("witherGlide", new FixedMetadataValue(plugin, true));

                                wither.setVelocity(player.getLocation().getDirection().normalize().multiply(2));

                                int intelligence = sPlayer.getIntelligence();
                                new BukkitRunnable() {
                                    int counter = 10;
                                    @Override
                                    public void run() {
                                        counter--;

                                        wither.setVelocity(player.getLocation().getDirection().normalize().multiply(2));

                                        for(Entity ent : wither.getNearbyEntities(3, 3, 3)) {
                                            if(ent instanceof LivingEntity) {
                                                if(!(ent instanceof Player)) {
                                                    ((LivingEntity) ent).damage(25000 + (intelligence * 50), player);
                                                }
                                            }
                                        }

                                        if(counter <= 0) {
                                            wither.remove();
                                            cancel();
                                        }
                                    }
                                }.runTaskTimer(plugin, 6, 6);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDismount(EntityDismountEvent e) {
        if(e.getEntity().getType() == EntityType.WITHER) {
            if(e.getEntity().hasMetadata("witherGlide")) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    private void onTarget(EntityTargetEvent e) {
        if(e.getEntity().getType() == EntityType.WITHER) {
            if(e.getEntity().hasMetadata("witherGlide")) {
                e.setCancelled(true);
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
