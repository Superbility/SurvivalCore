package com.superdevelopment.survivalcore.utils;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;

import java.util.ArrayList;
import java.util.List;

public class SetPlaceholders {
    public static List<String> setPlaceholders(List<String> lore, SurvivalPlayer player) {
        List<String> newLore = new ArrayList();
        for(String s : lore) {
            newLore.add(s
                    .replace("{maximum_health}", String.valueOf(player.getMaxHealth()))
                    .replace("{defense}", String.valueOf(player.getDefense()))
                    .replace("{damage_reduction}", String.valueOf(player.getDamageReductionPercentage()))
                    .replace("{speed}", String.valueOf(player.getSpeed()))
                    .replace("{luckychance}", String.valueOf(player.getLuckyChance()))
                    .replace("{critdamage}", String.valueOf(player.getCritDamage()))
                    .replace("{force}", String.valueOf(player.getForce())));
        }
        return newLore;
    }
    public static List<String> setCustomPlaceholder(List<String> lore, String before, String after) {
        List<String> newLore = new ArrayList();
        for(String s : lore) {
            newLore.add(s.replace(before, after));
        }
        return newLore;
    }
}
