package com.superdevelopment.survivalcore.utils;

import java.util.ArrayList;
import java.util.List;

public class ChatColor {
    public static String colorise(String message) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
    }
    public static List<String> colorise(List<String> lore) {
        List<String> newLore = new ArrayList<>();
        for(String s : lore) {
            newLore.add(org.bukkit.ChatColor.translateAlternateColorCodes('&', s));
        }
        return newLore;
    }
}
