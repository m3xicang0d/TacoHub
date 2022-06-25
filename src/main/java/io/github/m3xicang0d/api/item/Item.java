package io.github.m3xicang0d.api.item;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Item {

    public abstract ItemStack getItem();

    public void onPlayerInteract(PlayerInteractEvent event) {}

    public void give(Player player) {
        player.getInventory().addItem(getItem());
        player.updateInventory();
    }

    public void set(Player player, int slot) {
        player.getInventory().setItem(slot, getItem());
        player.updateInventory();
    }
}
