package com.superdevelopment.survivalcore.listeners.abilities;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.abilities.Ability;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class SpiritWaveAbility implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    private int time = plugin.getConfig().getInt("Abilities.SpiritWave.cooldown");
    private int mana = plugin.getConfig().getInt("Abilities.SpiritWave.mana-cost");

    @EventHandler
    private void onRightClick(PlayerInteractEvent e) {
        SurvivalPlayer sPlayer = GetSurvivalPlayer.getSurvivalPlayer(e.getPlayer());
        Player player = e.getPlayer();
        if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(e.getItem());
            if (nbtItem.hasKey("Ability")) {
                Ability ability = nbtItem.getObject("Ability", Ability.class);
                if (ability == Ability.SPIRIT_WAVE) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (!hasCooldown(player)) {
                            if (hasIntelligence(sPlayer, player)) {
                                cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (time * 1000));

                                FallingBlock block = player.getWorld().spawnFallingBlock(player.getEyeLocation(), Material.GOLD_BLOCK, (byte) 0);
                                block.setVelocity(player.getLocation().getDirection().normalize().multiply(2));
                                block.setMetadata("spiritWave", new FixedMetadataValue(plugin, true));

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if(block != null) {
                                            for(Entity entity : block.getNearbyEntities(1, 1, 1)) {
                                                if (entity instanceof LivingEntity) {
                                                    if (!(entity instanceof Player)) {
                                                        ((LivingEntity) entity).damage(13000 + (30 * sPlayer.getIntelligence()), player);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }.runTaskTimer(plugin, 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onLand(EntityChangeBlockEvent e) {
        if(e.getEntity().hasMetadata("spiritWave")) {
            if(e.getEntity() instanceof FallingBlock) {
                for(Entity entity : e.getEntity().getNearbyEntities(5, 5, 5)) {
                    if (entity instanceof LivingEntity) {
                        ((LivingEntity) entity).damage(13000);
                    }
                }
                Location location = e.getBlock().getLocation();
                e.getEntity().getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 0, false, false);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getEntity().remove();
                        e.getBlock().setType(Material.AIR);
                        e.getBlock().getWorld().getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
                    }
                }.runTaskLater(plugin, 5);
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
