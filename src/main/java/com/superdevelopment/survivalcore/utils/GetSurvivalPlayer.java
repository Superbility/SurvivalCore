package com.superdevelopment.survivalcore.utils;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GetSurvivalPlayer {
    private static Main plugin = Main.getPlugin(Main.class);

    public static SurvivalPlayer getSurvivalPlayer(Player player) {
        return getSurvivalPlayer(player.getUniqueId());
    }
    public static SurvivalPlayer getSurvivalPlayer(UUID uuid) {
        return plugin.survivalPlayers.get(uuid);
    }
}
