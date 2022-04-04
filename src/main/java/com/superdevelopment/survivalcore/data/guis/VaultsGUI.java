package com.superdevelopment.survivalcore.data.guis;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.utils.ChatColor;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VaultsGUI {
    public static Inventory getInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Vaults");

        ItemStack fillItem = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta fillItemMeta = fillItem.getItemMeta();
        fillItemMeta.setDisplayName("");
        fillItem.setItemMeta(fillItemMeta);

        inv.setItem(0, fillItem);
        inv.setItem(8, fillItem);
        inv.setItem(45, fillItem);
        inv.setItem(53, fillItem);

        int vaultsAmount = getVaultsAmount(player);



        for(int i = 1; i < 51; ++i) {
            if(vaultsAmount > i) { //has vault
                ItemStack unlockedVault = new ItemStack(XMaterial.CHEST_MINECART.parseMaterial());
                ItemMeta unlockedVaultMeta = unlockedVault.getItemMeta();
                unlockedVaultMeta.setDisplayName(ChatColor.colorise("&a&lVault " + i));
                unlockedVault.setItemMeta(unlockedVaultMeta);

                NBTItem unlockedVaultNbt = new NBTItem(unlockedVault);
                unlockedVaultNbt.setInteger("Vault", i);
                unlockedVault = unlockedVaultNbt.getItem();

                inv.addItem(unlockedVault);
            } else {
                ItemStack lockedVault = new ItemStack(XMaterial.MINECART.parseMaterial());
                ItemMeta lockedVaultMeta = lockedVault.getItemMeta();
                lockedVaultMeta.setDisplayName(ChatColor.colorise("&c&lVault " + i));
                lockedVault.setItemMeta(lockedVaultMeta);

                inv.addItem(lockedVault);
            }
        }

        return inv;
    }
    private static int getVaultsAmount(Player player) {
        int amount = 1;
        for(int x = 51; x > 0; x--){
            if(player.hasPermission("survival.vaults." + x)) {
                amount = x;
                break;
            }
        }
        return amount;
    }
}
