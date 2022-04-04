package com.superdevelopment.survivalcore.listeners;

import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.DataConfig;
import com.superdevelopment.survivalcore.data.guis.ModeSelectionGUI;
import com.superdevelopment.survivalcore.data.guis.SurvivalMenu;
import com.superdevelopment.survivalcore.data.items.MenuItem;
import com.superdevelopment.survivalcore.utils.GetSurvivalPlayer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractListener implements Listener {
    private SurvivalMenu survivalMenu = new SurvivalMenu();
    private DataConfig dataCfg = new DataConfig();

    @EventHandler //Open Menu
    private void onMenuInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if(e.getItem() != null && e.getItem().getType() != Material.AIR) {
                NBTItem item = new NBTItem(e.getItem());
                if(item.hasKey("SURVIVAL_KEY") && item.getString("SURVIVAL_KEY").equals(MenuItem.itemKey)) {
                    if(!dataCfg.getConfig().contains(player.getUniqueId() + ".AdventureMode")) {
                        player.openInventory(ModeSelectionGUI.getInventory());
                    } else {
                        e.getPlayer().openInventory(survivalMenu.getMainMenu());
                    }
                }
            }
        }
    }
}
