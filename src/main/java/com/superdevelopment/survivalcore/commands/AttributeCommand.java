package com.superdevelopment.survivalcore.commands;

import com.superdevelopment.survivalcore.Main;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.configs.DataConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AttributeCommand implements CommandExecutor {
    private Main plugin = Main.getPlugin(Main.class);
    private DataConfig dataConfig = new DataConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("attribute")) {
            if(args.length > 3 && (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove"))) {
                String playerName = args[1];

                Player player = Bukkit.getPlayerExact(playerName);
                String stat = args[2];
                int amount = Integer.parseInt(args[3]);

                if (player == null || !player.hasPlayedBefore()) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("add")) {
                    if(stat.equalsIgnoreCase("MAXHEALTH")) {
                        int currentMaxHealth = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.MaxHealth");
                        if (currentMaxHealth + amount > 10000) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's health to this value! It would exceed the max health limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.MaxHealth", currentMaxHealth + amount);
                    } else if(stat.equalsIgnoreCase("DEFENSE")) {
                        int currentDefense = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Defense");
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Defense", currentDefense + amount);
                    } else if(stat.equalsIgnoreCase("INTELLIGENCE")) {
                        int currentIntelligence = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Intelligence");
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Intelligence", currentIntelligence + amount);
                    } else if(stat.equalsIgnoreCase("FORCE")) {
                        int currentForce = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Force");
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Force", currentForce + amount);
                    } else if(stat.equalsIgnoreCase("CRITDAMAGE")) {
                        int currentCritDamage = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.CritDamage");
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.CritDamage", currentCritDamage + amount);
                    } else if(stat.equalsIgnoreCase("LUCKYCHANCE")) {
                        int currentLuckyChance = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.LuckyChance");
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.LuckyChance", currentLuckyChance + amount);
                    } else if(stat.equalsIgnoreCase("SPEED")) {
                        int currentSpeed = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Speed");
                        if (currentSpeed + amount > 200) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's speed to this value! It would exceed the max speed limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Speed", currentSpeed + amount);
                    } else if(stat.equalsIgnoreCase("AGILITY")) {
                        int currentAgility = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Agility");
                        if (currentAgility + amount > 100) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's agility to this value! It would exceed the max agility limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Agility", currentAgility + amount);
                    } else {
                        sender.sendMessage(com.superdevelopment.survivalcore.utils.ChatColor
                                .colorise("&cNo stat with the name '" + stat + "' was found! List of available stats: &aMAXHEALTH, DEFENSE, INTELLIGENCE, FORCE, CRITDAMAGE, LUCKYCHANCE, SPEED, AGILITY"));
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (stat.equalsIgnoreCase("MAXHEALTH")) {
                        int currentMaxHealth = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.MaxHealth");
                        if (currentMaxHealth - amount < 100) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's health to this value! It would exceed the min health limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.MaxHealth", currentMaxHealth - amount);
                    } else if (stat.equalsIgnoreCase("DEFENSE")) {
                        int currentDefense = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Defense");
                        if (currentDefense - amount < 0) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's defense to this value! It would exceed the min defense limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Defense", currentDefense - amount);
                    } else if (stat.equalsIgnoreCase("INTELLIGENCE")) {
                        int currentIntelligence = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Intelligence");
                        if (currentIntelligence - amount < 50) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's intelligence to this value! It would exceed the min intelligence limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Intelligence", currentIntelligence - amount);
                    } else if (stat.equalsIgnoreCase("FORCE")) {
                        int currentForce = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Force");
                        if (currentForce - amount < 10) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's force to this value! It would exceed the min force limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Force", currentForce - amount);
                    } else if (stat.equalsIgnoreCase("CRITDAMAGE")) {
                        int currentCritDamage = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.CritDamage");
                        if (currentCritDamage - amount < 25) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's crit damage to this value! It would exceed the min crit damage limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.CritDamage", currentCritDamage - amount);
                    } else if (stat.equalsIgnoreCase("LUCKYCHANCE")) {
                        int currentLuckyChance = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.LuckyChance");
                        if (currentLuckyChance - amount < 25) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's lucky chance to this value! It would exceed the min lucky chance limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.LuckyChance", currentLuckyChance - amount);
                    } else if (stat.equalsIgnoreCase("SPEED")) {
                        int currentSpeed = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Speed");
                        if (currentSpeed - amount < 0) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's speed to this value! It would exceed the min speed limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Speed", currentSpeed - amount);
                    } else if (stat.equalsIgnoreCase("AGILITY")) {
                        int currentAgility = dataConfig.getConfig().getInt(player.getUniqueId() + ".Stats.Agility");
                        if (currentAgility - amount < 0) {
                            sender.sendMessage(ChatColor.RED + "Cannot set player's agility to this value! It would exceed the min agility limit");
                            return true;
                        }
                        dataConfig.getConfig().set(player.getUniqueId() + ".Stats.Agility", currentAgility - amount);
                    } else {
                        sender.sendMessage(com.superdevelopment.survivalcore.utils.ChatColor
                                .colorise("&cNo stat with the name '" + stat + "' was found! List of available stats: &aMAXHEALTH, DEFENSE, INTELLIGENCE, FORCE, CRITDAMAGE, LUCKYCHANCE, SPEED, AGILITY"));
                        return true;
                    }
                }
                dataConfig.saveFile();

                if(player.isOnline()) {
                    SurvivalPlayer sPlayer = plugin.survivalPlayers.get(player.getUniqueId());
                    sPlayer.resetStats();
                }

                sender.sendMessage(com.superdevelopment.survivalcore.utils.ChatColor.colorise(
                        "&6" + playerName + "'s " + stat + " &awas successfully changed by &6" + amount
                ));
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /attribute [add/remove] [player] [stat] [amount]");
                return true;
            }
        }
        return false;
    }
}
