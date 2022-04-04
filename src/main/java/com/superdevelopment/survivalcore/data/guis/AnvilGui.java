package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.items.abilities.ItemType;
import com.superdevelopment.survivalcore.data.items.builder.LoreBuilder;
import com.superdevelopment.survivalcore.data.items.builder.data.Enchant;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnvilGui {
    private static List<Integer> blackFillSlots = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 16, 17, 18, 19, 21, 23, 25, 26, 27, 28, 30, 31, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44));
    private static List<Integer> upgradePaneSlots = new ArrayList<>(Arrays.asList(11, 12, 20));
    private static List<Integer> sacrificePaneSlots = new ArrayList<>(Arrays.asList(14, 15, 24));
    private static List<Integer> coloredPaneSlots = new ArrayList<>(Arrays.asList(45, 46, 47, 48, 49, 50, 51, 52, 53));


    public static Inventory getInventory(ItemStack leftItem, ItemStack rightItem, ItemStack finalItem) {
        Inventory tempInv = Bukkit.createInventory(null, 54, "");

        ItemStack blackFill = new ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta blackFillMeta = blackFill.getItemMeta();
        blackFillMeta.setDisplayName(" ");
        blackFill.setItemMeta(blackFillMeta);
        NBTItem blackFillNbt = new NBTItem(blackFill);
        blackFillNbt.setBoolean("clickable", false);
        blackFill = blackFillNbt.getItem();


        ItemStack sacrificePanes = null;
        ItemStack upgradePanes = null;
        ItemStack coloredPanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());

        if (leftItem != null && rightItem != null) {
            NBTItem rightItemNbt = new NBTItem(rightItem);
            if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                Enchant enchant = rightItemNbt.getObject("enchant", Enchant.class);
                int tier = rightItemNbt.getInteger("tier");
                if(ItemType.getType(leftItem) != null) {
                    if (Enchant.canEnchant(leftItem, enchant, tier)) {
                        upgradePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                        sacrificePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                        coloredPanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                    } else {
                        upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                        sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                    }
                } else {
                    if (leftItem != null) {
                        if(ItemType.getType(leftItem) != null) {
                            upgradePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                        } else {
                            upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                        }
                    } else {
                        upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                    }
                    if (rightItem != null) {
                        if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                            sacrificePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                        } else {
                            sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                        }
                    } else {
                        sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                    }
                }
            } else {
                if (leftItem != null) {
                    if(ItemType.getType(leftItem) != null) {
                        upgradePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                    } else {
                        upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                    }
                } else {
                    upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                }
                if (rightItem != null) {
                    if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                        sacrificePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                    } else {
                        sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                    }
                } else {
                    sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                }
            }
        } else {
            if (leftItem != null) {
                if(ItemType.getType(leftItem) != null) {
                    upgradePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                } else {
                    upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                }
            } else {
                upgradePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
            }
            if (rightItem != null) {
                NBTItem rightItemNbt = new NBTItem(rightItem);
                if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                    sacrificePanes = new ItemStack(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial());
                } else {
                    sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
                }
            } else {
                sacrificePanes = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
            }
        }
        ItemMeta upgradePanesMeta = upgradePanes.getItemMeta();
        upgradePanesMeta.setDisplayName(ChatColor.colorise("&6Item To Upgrade"));
        upgradePanesMeta.setLore(ChatColor.colorise(Arrays.asList("&7The item you want to upgrade", "&7should be placed in the slot on", "&7this side")));
        upgradePanes.setItemMeta(upgradePanesMeta);
        NBTItem upgradePanesNbt = new NBTItem(upgradePanes);
        upgradePanesNbt.setBoolean("clickable", false);
        upgradePanes = upgradePanesNbt.getItem();

        ItemMeta sacrificePanesMeta = sacrificePanes.getItemMeta();
        sacrificePanesMeta.setDisplayName(ChatColor.colorise("&6Item To Sacrifice"));
        sacrificePanesMeta.setLore(ChatColor.colorise(Arrays.asList("&7The item you are sacrificing in", "&7order to upgrade the item on the", "&7left should be placed in the", "&7slot on this side")));
        sacrificePanes.setItemMeta(sacrificePanesMeta);
        NBTItem sacrificePanesNbt = new NBTItem(sacrificePanes);
        sacrificePanesNbt.setBoolean("clickable", false);
        sacrificePanes = sacrificePanesNbt.getItem();

        ItemMeta coloredPanesMeta = coloredPanes.getItemMeta();
        coloredPanesMeta.setDisplayName(ChatColor.colorise(" "));
        coloredPanes.setItemMeta(coloredPanesMeta);
        NBTItem coloredPanesNbt = new NBTItem(coloredPanes);
        coloredPanesNbt.setBoolean("clickable", false);
        coloredPanes = coloredPanesNbt.getItem();

        ItemStack anvilItem = new ItemStack(XMaterial.BARRIER.parseMaterial());
        if (finalItem == null) {
            ItemMeta anvilItemMeta = anvilItem.getItemMeta();
            anvilItemMeta.setDisplayName(ChatColor.colorise("&cAnvil"));
            anvilItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Place a target item in the left", "&7slot and a sacrifice item in the", "&7right slot to combine Enchantments!")));
            anvilItem.setItemMeta(anvilItemMeta);
            if (leftItem != null && rightItem != null) {
                NBTItem rightItemNbt = new NBTItem(rightItem);
                if (ItemType.getType(leftItem) != null) {
                    if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                        Enchant enchant = rightItemNbt.getObject("enchant", Enchant.class);
                        int tier = rightItemNbt.getInteger("tier");
                        if (Enchant.canEnchant(leftItem, enchant, tier)) {
                            anvilItem = Enchant.addEnchant(leftItem.clone(), enchant, tier);
                            anvilItemMeta = anvilItem.getItemMeta();
                            LoreBuilder builder = new LoreBuilder(anvilItem);
                            List<String> lore = builder.buildLore();
                            lore.add("");
                            lore.add("&7&b&m------------------");
                            lore.add("");
                            lore.add("&aThis is the item you will get.");
                            lore.add("&aClick the &cANVIL BELOW &ato");
                            lore.add("&acombine.");
                            anvilItemMeta.setLore(ChatColor.colorise(lore));
                            anvilItem.setItemMeta(anvilItemMeta);
                        } else {
                            anvilItemMeta.setDisplayName(ChatColor.colorise("&cError!"));
                            anvilItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7You cannot add that", "&7enchantment to that item!")));
                            anvilItem.setItemMeta(anvilItemMeta);
                        }
                        anvilItemMeta.setDisplayName(ChatColor.colorise("&cError!"));
                        anvilItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7You cannot add that", "&7enchantment to that item!")));
                        anvilItem.setItemMeta(anvilItemMeta);
                    }
                }
            }
            NBTItem anvilItemNbt = new NBTItem(anvilItem);
            anvilItemNbt.setBoolean("clickable", false);
            anvilItem = anvilItemNbt.getItem();
        } else {
            anvilItem = finalItem;
        }

        ItemStack combineItem = new ItemStack(XMaterial.ANVIL.parseMaterial());
        ItemMeta combineItemMeta = combineItem.getItemMeta();
        combineItemMeta.setDisplayName(ChatColor.colorise("&aCombine Items"));
        combineItemMeta.setLore(ChatColor.colorise(Arrays.asList("&7Combine the items in the slots", "&7to the left and right below")));
        if (leftItem != null && rightItem != null) {
            NBTItem rightItemNbt = new NBTItem(rightItem);
            if (rightItemNbt.hasKey("enchant") && rightItemNbt.hasKey("tier")) {
                Enchant enchant = rightItemNbt.getObject("enchant", Enchant.class);
                int tier = rightItemNbt.getInteger("tier");
                if (Enchant.canEnchant(leftItem, enchant, tier)) {
                    List<String> lore = combineItemMeta.getLore();
                    lore.add("");
                    lore.add(ChatColor.colorise("&7Cost " + enchant.getCost(tier) + " &3EXP Levels"));
                    lore.add("");
                    lore.add(ChatColor.colorise("&eClick to combine!"));
                    combineItemMeta.setLore(lore);
                }
            }
        }
        combineItem.setItemMeta(combineItemMeta);
        NBTItem combineItemNbt = new NBTItem(combineItem);
        combineItemNbt.setBoolean("buy", true);
        combineItemNbt.setBoolean("clickable", false);
        combineItem = combineItemNbt.getItem();

        for (int i : blackFillSlots) {
            tempInv.setItem(i, blackFill);
        }
        for (int i : coloredPaneSlots) {
            tempInv.setItem(i, coloredPanes);
        }
        for (int i : upgradePaneSlots) {
            tempInv.setItem(i, upgradePanes);
        }
        for (int i : sacrificePaneSlots) {
            tempInv.setItem(i, sacrificePanes);
        }
        tempInv.setItem(13, anvilItem);
        tempInv.setItem(22, combineItem);
        tempInv.setItem(29, leftItem);
        tempInv.setItem(33, rightItem);

        return tempInv;
    }
}
