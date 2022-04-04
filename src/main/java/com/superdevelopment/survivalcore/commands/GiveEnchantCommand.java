package com.superdevelopment.survivalcore.commands;

import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class GiveEnchantCommand implements CommandExecutor {

    private static String usage = ChatColor.colorise("&cUsage: /genchant {player} {enchant} {tier}");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("genchant")) {
            if(args.length > 2) {
                String sPlayer = args[0];
                if(Bukkit.getPlayer(sPlayer) == null) {
                    sender.sendMessage(ChatColor.colorise("&cPlayer not found!"));
                    return true;
                }
                Player player = Bukkit.getPlayer(sPlayer);

                Enchant enchant = Enchant.valueOf(args[1].toUpperCase(Locale.ROOT));
                if(enchant == null) {
                    sender.sendMessage(ChatColor.colorise("&cEnchant not found!"));
                    return true;
                }

                int tier = Integer.valueOf(args[2]);

                player.getInventory().addItem(enchant.getItem(tier, false));

                sender.sendMessage(ChatColor.colorise("&aEnchant given successfully!"));
                return true;
            } else {
                sender.sendMessage(usage);
                return true;
            }
        }
        return false;
    }
}
