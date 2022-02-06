package me.jesusmx.tacohub.cosmetics.button.armor;

import me.jesusmx.tacohub.cosmetics.menu.armor.ArmorsMenu;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class ArmorMenuButton extends Button {

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        new ArmorsMenu().openMenu(player);
    }

    @Override
    public ItemStack getItem(Player player) {
        return null;
    }
}
