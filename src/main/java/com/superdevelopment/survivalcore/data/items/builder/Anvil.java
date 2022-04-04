package com.superdevelopment.survivalcore.data.items.builder;

import com.cryptomorin.xseries.XMaterial;
import com.superdevelopment.survivalcore.data.SurvivalPlayer;
import com.superdevelopment.survivalcore.data.guis.AnvilGui;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Anvil {
    private SurvivalPlayer player;
    private ItemStack leftItem;
    private ItemStack rightItem;
    private ItemStack finalItem;
    private Inventory inv;

    public Anvil(SurvivalPlayer player) {
        this.player = player;

        this.inv = Bukkit.createInventory(null, 54, "Anvil");
        updateInventory();

        this.player.getPlayer().openInventory(inv);
    }

    public void updateInventory() {
        this.leftItem = this.inv.getItem(29);
        this.rightItem = this.inv.getItem(33);
        this.inv.setContents(AnvilGui.getInventory(leftItem, rightItem, finalItem).getContents());
        if(new NBTItem(this.inv.getItem(13)).getBoolean("clickable")) this.finalItem = this.inv.getItem(13);
    }

    public void setLeftItem(ItemStack item) {
        this.inv.setItem(29, item);
        this.leftItem = item;
    }
    public ItemStack getLeftItem() {
        return this.leftItem;
    }

    public void setRightItem(ItemStack item) {
        this.inv.setItem(33, item);
        this.rightItem = item;
    }
    public ItemStack getRightItem() {
        return this.rightItem;
    }

    public void setFinalItem(ItemStack item) {
        //this.inv.setItem(13, item);
        this.finalItem = item;
    }
    public ItemStack getFinalItem() {
        return this.finalItem;
    }
}
