package com.superdevelopment.survivalcore.data.items.builder.data;

import com.superdevelopment.survivalcore.utils.ChatColor;

public enum Rarity {
    COMMON, UNCOMMON, RARE, VERY_RARE, EXTREMELY_RARE, LEGENDARY;

    public Rarity shuffleRarities() {
        switch(this) {
            case COMMON: return UNCOMMON;
            case UNCOMMON: return RARE;
            case RARE: return VERY_RARE;
            case VERY_RARE: return EXTREMELY_RARE;
            case EXTREMELY_RARE: return LEGENDARY;
            case LEGENDARY: return COMMON;
        }
        return null;
    }
    public String getDisplayName() {
        switch(this) {
            case COMMON: return ChatColor.colorise("&aCommon");
            case UNCOMMON: return ChatColor.colorise("&2Uncommon");
            case RARE: return ChatColor.colorise("&3Rare");
            case VERY_RARE: return ChatColor.colorise("&9Very Rare");
            case EXTREMELY_RARE: return ChatColor.colorise("&5Extremely Rare");
            case LEGENDARY: return ChatColor.colorise("&eLegendary");
        }
        return null;
    }
    public String getSelectedRarity(Rarity rarity) {
        switch(this) {
            case COMMON:
                if(rarity == COMMON) return ChatColor.colorise("&a » Common");
                else return ChatColor.colorise("&7» &aCommon");
            case UNCOMMON:
                if(rarity == UNCOMMON) return ChatColor.colorise("&a » &2Uncommon");
                else return ChatColor.colorise("&7» &2Uncommon");
            case RARE:
                if(rarity == RARE) return ChatColor.colorise("&a » &3Rare");
                else return ChatColor.colorise("&7» &3Rare");
            case VERY_RARE:
                if(rarity == VERY_RARE) return ChatColor.colorise("&a » &9Very Rare");
                else return ChatColor.colorise("&7» &9Very Rare");
            case EXTREMELY_RARE:
                if(rarity == EXTREMELY_RARE) return ChatColor.colorise("&a » &5Extremely Rare");
                else return ChatColor.colorise("&7» &5Extremely Rare");
            case LEGENDARY:
                if(rarity == LEGENDARY) return ChatColor.colorise("&a » &eLegendary");
                else return ChatColor.colorise("&7» &eLegendary");
        }
        return null;
    }
}
