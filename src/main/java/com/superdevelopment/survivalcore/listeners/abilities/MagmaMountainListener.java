package com.superdevelopment.survivalcore.listeners.abilities;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.api.Particles_1_13;
import com.github.fierioziy.particlenativeapi.api.Particles_1_8;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.utils.GetCircle;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MagmaMountainListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    private int time = plugin.getConfig().getInt("Abilities.MagmaMountain.cooldown");
    private int mana = plugin.getConfig().getInt("Abilities.MagmaMountain.mana-cost");

    @EventHandler
    private void onRightClick(PlayerInteractEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
        Player player = e.getPlayer();
        if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(e.getItem());
            if (nbtItem.hasKey("Ability")) {
                Ability ability = nbtItem.getObject("Ability", Ability.class);
                if (ability == Ability.MAGMA_MOUNTAIN) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (!hasCooldown(player)) {
                            if (hasIntelligence(sPlayer, player)) {
                                cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (time * 1000));

                                player.sendMessage(ChatColor.GREEN + "Magma-Mountain activated!");

                                List<Entity> entities = player.getNearbyEntities(5, 5, 5);

                                startParticles(sPlayer);

                                for(Entity entity : entities) {
                                    if(entity instanceof LivingEntity) {
                                        if (!(entity instanceof Player)) {
                                            ((LivingEntity) entity).damage(15000 + (sPlayer.getIntelligence() * 10), player);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void startParticles(SurvivalPlayer sPlayer) {
        Player player = sPlayer.getPlayer();

        ParticleNativeAPI api = ParticleNativeCore.loadAPI(plugin);
        Particles_1_13 particles = api.getParticles_1_13();

            player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
            new BukkitRunnable() {
                double t = Math.PI / 4;
                Location loc = player.getLocation().add(0.5, 0, 0.5);

                public void run() {
                    t = t + 0.1 * Math.PI;
                    for (double theta = 0; theta <= 2 * Math.PI; theta = theta + Math.PI / 32) {
                        double x = t * cos(theta);
                        double y = 2 * Math.exp(-0.1 * t) * sin(t) + 1.5;
                        double z = t * sin(theta);
                        Object packet1 = particles.FLAME().packet(true, loc.clone().add(x, y, z));

                        theta = theta + Math.PI / 64;

                        x = t * cos(theta);
                        y = 2 * Math.exp(-0.1 * t) * sin(t) + 1.5;
                        z = t * sin(theta);
                        Object packet2 = particles.DRIPPING_LAVA().packet(true, loc.clone().add(x, y, z));

                        for(Player p : Bukkit.getOnlinePlayers()) {
                            if(loc.distance(p.getLocation()) < 40) {
                                particles.sendPacket(p, packet1);
                                particles.sendPacket(p, packet2);
                            }
                        }
                    }
                    if (t > 20) {
                        this.cancel();
                    }
                } //Particles
            }.runTaskTimer(plugin, 0, 1); //particles
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
