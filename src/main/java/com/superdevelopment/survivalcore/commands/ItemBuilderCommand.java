package com.superdevelopment.survivalcore.commands;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.guis.BuilderGUI;
import com.superdevelopment.survivalcore.data.items.builder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemBuilderCommand implements CommandExecutor {
    private Main plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("itembuilder")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.getItemInHand() != null && player.getItemInHand().getType() != XMaterial.AIR.parseMaterial()) {
                    ItemBuilder itemBuilder = new ItemBuilder(player.getItemInHand().clone(), player);
                    Inventory itemBuilderInv = BuilderGUI.getInventory(itemBuilder);
                    player.openInventory(itemBuilderInv);

                    player.setItemInHand(new ItemStack(Material.AIR));

                    plugin.itemBuildingPlayers.put(player.getUniqueId(), itemBuilder);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "You must be holding an item to do this!");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                return true;
            }
        }
        return false;
    }
}
