package me.jesusmx.tacohub.selector.hub.button;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.menus.HubSelectorFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class HubButton extends Button {

    private final FileConfiguration config = HubSelectorFile.getConfig();
    private final String server;

    public HubButton(String server) {
        this.server = server;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.valueOf(config.getString(d("ITEM"))))
                .name(config.getString(d("NAME")))
                .lore(PlaceholderAPI.setPlaceholders(player, config.getStringList(d("LORE"))))
                .data(config.getInt(d("DATA")))
                .build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        TacoHub.getInstance().getQueueManager().sendPlayer(player, server);
    }

    private String d(String a) {
        return "HUB-SELECTOR.ITEMS." + server + "." + a;
    }
}
