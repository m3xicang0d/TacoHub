package me.jesusmx.tacohub.selector.server.button;

import lombok.AllArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.selector.subselector.menu.SubSelectorMenu;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bungee.BungeeUtils;
import me.jesusmx.tacohub.utils.files.menus.ServerSelectorFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@AllArgsConstructor
public class ServerButton extends Button {

    private String server;
    private final FileConfiguration config = ServerSelectorFile.getConfig();

    @Override
    public ItemStack getItem(Player player) {
        if (config.getBoolean(d("HEAD.ENABLED"))) {
            //System.out.println(server);
            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setOwner(config.getString(d("HEAD.NAME")));
            skull.setDisplayName(config.getString(d("NAME")));
            skull.setLore(PlaceholderAPI.setPlaceholders(player, config.getStringList(d("LORE"))));
            item.setItemMeta(skull);
            return item;
        } else {
            return new ItemBuilder(Material.valueOf(config.getString(d("ITEM"))))
                    .name(config.getString(d("NAME")))
                    .lore(PlaceholderAPI.setPlaceholders(player, config.getStringList(d("LORE"))))
                    .data(config.getInt(d("DATA")))
                    .build();
        }
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if(config.getBoolean("SERVER-SELECTOR.ITEMS." + server + ".SUB-SERVER")) {
            new SubSelectorMenu(server).openMenu(player);
        } else {
            if(config.getBoolean("SERVER-SELECTOR.ITEMS." + server + ".COMMAND.ENABLED")) {
                Bukkit.dispatchCommand(player, config.getString("SERVER-SELECTOR.ITEMS." + server + ".COMMAND.COMMAND"));
            } else {
                TacoHub.getInstance().getQueueManager().sendPlayer(player, server);
            }
        }
    }

    private String d(String a) {
        return "SERVER-SELECTOR.ITEMS." + server + "." + a;
    }
}

