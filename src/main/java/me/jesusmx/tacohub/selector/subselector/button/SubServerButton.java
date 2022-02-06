package me.jesusmx.tacohub.selector.subselector.button;

import lombok.AllArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.menus.SubSelectorFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@AllArgsConstructor
public class SubServerButton extends Button {

    private String mServer;
    private String server;
    private final FileConfiguration config = SubSelectorFile.getConfig();

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
        if(config.getBoolean("SUB-SELECTOR.ITEMS." + server + ".COMMAND.ENABLED")) {
            Bukkit.dispatchCommand(player, config.getString("SUB-SELECTOR.ITEMS." + server + ".COMMAND.COMMAND"));
        } else {
            TacoHub.getInstance().getQueueManager().sendPlayer(player, server);
        }
    }

    private String d(String a) {
        return "SUB-SELECTOR." + mServer + "." + server + "." + a;
    }
}
