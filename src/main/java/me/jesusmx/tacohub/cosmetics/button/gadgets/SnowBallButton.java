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
 * @Author JesusMX
 * @Project TacoHub
 **/

public class SnowBallButton extends Button {

    private final FileConfiguration config = GadgetsFile.getConfig();

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.getInventory().addItem(new ItemBuilder(getItem(player)).setAmount(64).build());
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.SNOW_BALL)
                .name(config.getString("MENU.SNOW.NAME"))
                .lore(config.getStringList("MENU.SNOW.LORE"))
                .build();
    }
}