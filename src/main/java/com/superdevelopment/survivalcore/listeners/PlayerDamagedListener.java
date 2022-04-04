package com.superdevelopment.survivalcore.listeners;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerDamagedListener implements Listener {
    @EventHandler
    private void onPlayerDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getEntity());
            if(e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) e.setDamage(55);
            else if(e.getCause() == EntityDamageEvent.DamageCause.CONTACT) e.setDamage(15);
            else if(e.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) e.setDamage(180);
            else if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING) e.setDamage(20);
            else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) e.setDamage(105);
            else if(e.getCause() == EntityDamageEvent.DamageCause.FALL) e.setDamage(15 + (e.getEntity().getFallDistance() * 2));
            else if(e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) e.setDamage(30);
            else if(e.getCause() == EntityDamageEvent.DamageCause.FIRE) e.setDamage(15);
            else if(e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) e.setDamage(5);
            else if(e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) e.setDamage(20);
            else if(e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) e.setDamage(10);
            else if(e.getCause() == EntityDamageEvent.DamageCause.LAVA) e.setDamage(25);
            else if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) e.setDamage(225);
            else if(e.getCause() == EntityDamageEvent.DamageCause.MAGIC) e.setDamage(150);
            else if(e.getCause() == EntityDamageEvent.DamageCause.POISON) e.setDamage(player.getMaxHealth() * 0.02);
            else if(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) e.setDamage(35);
            else if(e.getCause() == EntityDamageEvent.DamageCause.STARVATION) e.setDamage(5);
            else if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) e.setDamage(35);
            else if(e.getCause() == EntityDamageEvent.DamageCause.SUICIDE) e.setDamage(player.getMaxHealth());
            else if(e.getCause() == EntityDamageEvent.DamageCause.VOID) e.setDamage(25);
            else if(e.getCause() == EntityDamageEvent.DamageCause.WITHER) e.setDamage(10);
        }
    }
    @EventHandler
    private void onPlayerDamagedByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            SurvivalPlayer player = GetSurvivalPlayer.getSurvivalPlayer((Player) e.getEntity());
            EntityType damagerType = e.getDamager().getType();
            Entity damager = e.getDamager();

            int damage = 0;

            if (damagerType == EntityType.ZOMBIE || damagerType == EntityType.SPIDER || damagerType == EntityType.SKELETON) {
                damage = 15;
            }
            else if(damagerType == EntityType.SILVERFISH || damagerType == EntityType.ENDERMITE || damagerType == EntityType.CAVE_SPIDER) {
                damage = 10;
            }
            else if(damagerType == EntityType.WITHER_SKELETON || damagerType == EntityType.BLAZE) {
                damage = 40;
            }
            else if(damagerType == EntityType.ENDERMAN) {
                damage = 80;
            }
            else if(damagerType == EntityType.CREEPER) {
                damage = 70;
            }
            else if(damagerType == EntityType.ENDER_DRAGON) {
                damage = 3000;
            }
            else {
                if(damager instanceof LivingEntity) {
                    damage = (int) (((LivingEntity) damager).getMaxHealth() * 0.6);
                }
            }

            if(damagerType == EntityType.WITHER || damagerType == EntityType.WITHER_SKELETON || damagerType == EntityType.WITHER_SKULL) {
                ItemStack[] gear = player.getPlayer().getInventory().getArmorContents();

                for(ItemStack i : gear) {
                    HashMap<Enchant, Integer> enchants = Enchant.getItemEnchants(i);
                    if(enchants.containsKey(Enchant.WITHER_GUARD)) {
                        damage = (int) (damage * (1 - (0.05 * enchants.get(Enchant.WITHER_GUARD))));
                    }
                }
            }

            player.damagePlayer(damage, damager);

            e.setCancelled(true);
        }
    }
}