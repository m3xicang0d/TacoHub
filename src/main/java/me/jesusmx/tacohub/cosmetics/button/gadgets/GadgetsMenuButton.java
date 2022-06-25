package me.jesusmx.tacohub.cosmetics.button.gadgets;

import me.jesusmx.tacohub.cosmetics.menu.gadgets.GadgetsMenu;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.features.CosmeticsFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

public class GadgetsMenuButton extends Button {

    private final FileConfiguration config = CosmeticsFile.getConfig();

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        new GadgetsMenu().openMenu(player);
    }

    @Override
    public ItemStack getItem(Player player) {
        String s = "COSMETICS.GADGETS.";
        return new ItemBuilder(Material.valueOf(config.getString(s + "ITEM")))
                .name(config.getString(s + "NAME"))
                .lore(config.getStringList( s + "LORE"))
                .data(config.getInt(s + "DATA"))
                .build();
    }
}