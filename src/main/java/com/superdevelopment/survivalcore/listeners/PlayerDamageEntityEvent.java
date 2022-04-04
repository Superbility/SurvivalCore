package com.superdevelopment.survivalcore.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerDamageEntityEvent implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    private void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            if(e.getEntity().hasMetadata("HITMARKER")) return;

            SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getDamager());
            double damage;

            if(((Player) e.getDamager()).getItemInHand() != null && ((Player) e.getDamager()).getItemInHand().getType() != XMaterial.AIR.parseMaterial()) {
                NBTItem item = new NBTItem(((Player) e.getDamager()).getItemInHand());
                if(item.hasKey("ItemBaseDamage")) {
                    damage = item.getInteger("ItemBaseDamage");
                } else {
                    damage = e.getDamage();
                }
            } else {
                damage = e.getDamage();
            }

            double force = (player.getForce() / 5 * 0.02) + 1;
            int critChance = player.getLuckyChance();
            double critDamage = (player.getCritDamage() / 100) + 1;
            int agilityHit = player.getAgility();

            boolean critStrike = false;
            boolean agilityStrike = false;

            if(force > 0) damage = damage * player.getForce();

            ItemStack item = ((Player) e.getDamager()).getItemInHand();
            if(item != null && item.getType() != XMaterial.AIR.parseMaterial()) {
                HashMap<Enchant, Integer> enchants = Enchant.getItemEnchants(item);
                if(enchants.containsKey(Enchant.SHARPNESS)) damage = damage * (1 + (0.1 * enchants.get(Enchant.SHARPNESS)));
                if(enchants.containsKey(Enchant.VAMPIRE)) player.getPlayer().setHealth(player.getPlayer().getHealth() + (0.005 * enchants.get(Enchant.VAMPIRE) * damage));
                if(enchants.containsKey(Enchant.CRITICAL_STRIKE)) critChance = (int) (critChance * (1 + (0.05 * enchants.get(Enchant.CRITICAL_STRIKE))));
                if(enchants.containsKey(Enchant.END_STRIKE)) {
                    if(e.getEntityType() == EntityType.ENDER_DRAGON || e.getEntityType() == EntityType.ENDERMAN || e.getEntityType() == EntityType.ENDERMITE) {
                        damage = damage * (1 + (0.1 * enchants.get(Enchant.END_STRIKE)));
                    }
                }
                if(enchants.containsKey(Enchant.BOA)) {
                    if(e.getEntityType() == EntityType.SPIDER || e.getEntityType() == EntityType.CAVE_SPIDER || e.getEntityType() == EntityType.SILVERFISH || e.getEntityType() == EntityType.ENDERMITE) {
                        damage = damage * (1 + (0.15 * enchants.get(Enchant.BOA)));
                    }
                }
                if(enchants.containsKey(Enchant.SMITE)) {
                    if(e.getEntityType() == EntityType.SKELETON || e.getEntityType() == EntityType.ZOMBIE || e.getEntityType() == EntityType.ZOMBIE_VILLAGER || e.getEntityType() == EntityType.HUSK || e.getEntityType() == EntityType.PHANTOM || e.getEntityType() == EntityType.DROWNED || e.getEntityType() == EntityType.ZOGLIN || e.getEntityType() == EntityType.WITHER || e.getEntityType() == EntityType.WITHER_SKELETON || e.getEntityType() == EntityType.ZOMBIFIED_PIGLIN || e.getEntityType() == EntityType.SKELETON_HORSE || e.getEntityType() == EntityType.ZOMBIE_HORSE || e.getEntityType() == EntityType.STRAY) {
                        damage = damage * (1 + (0.15 * enchants.get(Enchant.SMITE)));
                    }
                }
            }

            if(ThreadLocalRandom.current().nextInt(1, 100) <= critChance) {
                damage = damage * critDamage;
                critStrike = true;
            }
            if(ThreadLocalRandom.current().nextInt(1, 100) <= agilityHit) {
                damage = damage * 2;
                agilityStrike = true;
            }

            e.setDamage(damage);

            createHitmarker(damage, critStrike, agilityStrike, e.getEntity());
        }
    }

    private void createHitmarker(double damage, boolean critStrike, boolean agilityStrike, Entity ent) {
        String name = getName(Integer.valueOf((int) damage), critStrike, agilityStrike);
        ArmorStand stand = (ArmorStand) ent.getWorld().spawnEntity(ent.getLocation()
                .add(ThreadLocalRandom.current().nextDouble(-0.5, 0.5),
                        ThreadLocalRandom.current().nextDouble(-0.5, 0.5),
                        ThreadLocalRandom.current().nextDouble(-0.5, 0.5)), EntityType.ARMOR_STAND);
        stand.setVisible(false);
        stand.setSmall(true);
        stand.setGravity(false);
        stand.setGliding(true);
        stand.setCustomName(name);
        stand.setCustomNameVisible(true);
        stand.setMetadata("HITMARKER", new FixedMetadataValue(plugin, true));

        removeStand(stand);
    }
    private void removeStand(ArmorStand stand) {
        new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
            }
        }.runTaskLater(plugin, 40);
    }
    private String getName(int damage, boolean critStrike, boolean agilityStrike) {
        String message = "";
        boolean dark = false;
        if (critStrike && !agilityStrike) {
            char[] chars = String.valueOf(damage).toCharArray();
            for(char c : chars) {
                if(dark) {
                    dark = false;
                    message = message + ChatColor.RED + c;
                } else {
                    dark = true;
                    message = message + ChatColor.YELLOW + c;
                }
            }
        }
        if (agilityStrike && !critStrike) {
            char[] chars = String.valueOf(damage).toCharArray();
            for(char c : chars) {
                if(dark) {
                    dark = false;
                    message = message + ChatColor.DARK_BLUE + c;
                } else {
                    dark = true;
                    message = message + ChatColor.BLUE + c;
                }
            }
        }
        if (agilityStrike && critStrike) {
            char[] chars = String.valueOf(damage).toCharArray();
            for(char c : chars) {
                if(dark) {
                    dark = false;
                    message = message + org.bukkit.ChatColor.DARK_PURPLE + c;
                } else {
                    dark = true;
                    message = message + org.bukkit.ChatColor.LIGHT_PURPLE + c;
                }
            }
        }
        if(!agilityStrike && !critStrike) {
            message = message + org.bukkit.ChatColor.WHITE + damage;
        }
        return message;
    }
}
