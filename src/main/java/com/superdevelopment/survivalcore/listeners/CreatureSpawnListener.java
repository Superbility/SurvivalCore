package com.superdevelopment.survivalcore.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {
    @EventHandler
    private void onSpawn(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();
        if(!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) {
        switch (entity.getType()) {
            case COW: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case SHEEP: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case WOLF: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case CHICKEN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case RABBIT: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case CAT: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case BAT: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case BEE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case COD: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case DOLPHIN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case DONKEY: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case FOX: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case HORSE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case LLAMA: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case MULE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case MUSHROOM_COW: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case OCELOT: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case PANDA: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case PARROT: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case PUFFERFISH: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case PIG: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case SALMON: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case SQUID: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);

            case ZOMBIE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
            case SKELETON: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
            case SPIDER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(75);
            case ENDERMAN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150);
            case SILVERFISH: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
            case CAVE_SPIDER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
            case WITHER_SKELETON: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(105);
            case GHAST: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(170);
            case BLAZE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            case CREEPER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(85);
            case ENDER_DRAGON: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(4500000);
            case ELDER_GUARDIAN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(800);
            case DROWNED: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
            case ENDERMITE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(35);
            case GUARDIAN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400);
            case HOGLIN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
            case EVOKER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(135);
            case HUSK: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(145);
            case ILLUSIONER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(130);
            case IRON_GOLEM: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1000);
            case MAGMA_CUBE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(180);
            case PHANTOM: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
            case PIGLIN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
            case POLAR_BEAR: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(180);
            case RAVAGER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(170);
            case SHULKER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
            case SLIME:
                if(entity.getType().equals(EntityType.SLIME)) {
                    Slime slime = (Slime) entity;
                    if (slime.getSize() == 1) entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
                    else if (slime.getSize() == 2) entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(110);
                    else if (slime.getSize() == 3) entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(140);
                }
            case SKELETON_HORSE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
            case SNOWMAN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            case STRAY: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(95);
            case STRIDER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(110);
            case ZOMBIE_HORSE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            case TROPICAL_FISH: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case VEX: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(130);
            case TRADER_LLAMA: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
            case PILLAGER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150);
            case TURTLE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            case PIGLIN_BRUTE: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(270);
            case VILLAGER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
            case VINDICATOR: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
            case WITHER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(4000000);
            case ZOGLIN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(110);
            case ZOMBIE_VILLAGER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
            case ZOMBIFIED_PIGLIN: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(265);
            case WITCH: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(115);
            case WANDERING_TRADER: entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        }
        entity.setHealth(entity.getMaxHealth());
        }
    }
}
