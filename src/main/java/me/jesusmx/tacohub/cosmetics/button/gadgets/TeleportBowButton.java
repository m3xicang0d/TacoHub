package me.jesusmx.tacohub.cosmetics.button.gadgets;

import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.features.GadgetsFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class TeleportBowButton extends Button {

    private final FileConfiguration config = GadgetsFile.getConfig();

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.getInventory().addItem(getItem(player));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.BOW)
                .name(config.getString("MENU.BOW.NAME"))
                .lore(config.getStringList("MENU.BOW.LORE"))
                .build();
    }
}