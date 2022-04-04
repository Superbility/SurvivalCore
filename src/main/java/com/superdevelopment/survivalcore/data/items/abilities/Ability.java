package com.superdevelopment.survivalcore.data.items.abilities;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.utils.ChatColor;

import java.util.List;

public enum Ability {
    TELEPORT, SOUL_STEAL, INSTANT_FIRE, RAPID_HEAL, MAGMA_MOUNTAIN, BULK_UP, ASSASSIN_STRIKE, SPIRIT_WAVE, WITHER_GLIDE, ENDER_MANIAC;

    private Main plugin = Main.getPlugin(Main.class);

    public List<String> getDescription() {
        switch (this) {
            case TELEPORT: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.Teleport.description"));
            case SOUL_STEAL: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.SoulSteal.description"));
            case INSTANT_FIRE: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.InstantFire.description"));
            case RAPID_HEAL: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.RapidHeal.description"));
            case MAGMA_MOUNTAIN: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.MagmaMountain.description"));
            case BULK_UP: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.BulkUp.description"));
            case ASSASSIN_STRIKE: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.AssassinStrike.description"));
            case SPIRIT_WAVE: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.SpiritWave.description"));
            case WITHER_GLIDE: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.WitherGlide.description"));
            case ENDER_MANIAC: return ChatColor.colorise(plugin.getConfig().getStringList("Abilities.EnderManiac.description"));
        }
        return null;
    }

    public int getManaCost() {
        switch (this) {
            case TELEPORT: return 1;
            case SOUL_STEAL: return 2;
            case INSTANT_FIRE: return 3;
            case RAPID_HEAL: return 4;
            case MAGMA_MOUNTAIN: return 5;
            case BULK_UP: return 6;
            case ASSASSIN_STRIKE: return 7;
            case SPIRIT_WAVE: return 8;
            case WITHER_GLIDE: return 9;
            case ENDER_MANIAC: return 10;
        }
        return 0;
    }
    public String getName() {
        switch (this) {
            case TELEPORT: return "Teleport";
            case SOUL_STEAL: return "Soul Steal";
            case INSTANT_FIRE: return "Instant Fire";
            case RAPID_HEAL: return "Rapid Heal";
            case MAGMA_MOUNTAIN: return "Magma Mountain";
            case BULK_UP: return "Bulk Up";
            case ASSASSIN_STRIKE: return "Assassin Strike";
            case SPIRIT_WAVE: return "Spirit Wave";
            case WITHER_GLIDE: return "Wither Glide";
            case ENDER_MANIAC: return "Ender Maniac";
        }
        return "None!";
    }
}
