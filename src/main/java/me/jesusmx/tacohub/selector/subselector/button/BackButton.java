package me.jesusmx.tacohub.selector.subselector.button;

import me.jesusmx.tacohub.selector.server.menu.ServerSelectorMenu;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.menus.SubSelectorFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class BackButton extends Button {

    private final FileConfiguration config = SubSelectorFile.getConfig();

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        new ServerSelectorMenu().openMenu(player);
    }

    @Override
    public ItemStack getItem(Player player) {
        return new
                ItemBuilder(Material.valueOf(config.getString("SUB-SELECTOR.BACK_BUTTON.ITEM")))
                .name(config.getString("SUB-SELECTOR.BACK_BUTTON.NAME"))
                .lore(config.getStringList("SUB-SELECTOR.BACK_BUTTON.LORE"))
                .data(config.getInt("SUB-SELECTOR.BACK_BUTTON.DATA"))
                .build();
    }
}
