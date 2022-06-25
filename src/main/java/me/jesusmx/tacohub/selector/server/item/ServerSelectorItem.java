package me.jesusmx.tacohub.selector.server.item;

import me.jesusmx.tacohub.selector.server.menu.ServerSelectorMenu;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.m3xicang0d.api.item.Item;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ServerSelectorItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("SERVER-SELECTOR.MATERIAL")))
                .name(config.getString("SERVER-SELECTOR.NAME"))
                .lore(config.getStringList("SERVER-SELECTOR.LORE"))
                .data(config.getInt("SERVER-SELECTOR.DATA"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        new ServerSelectorMenu().openMenu(event.getPlayer());
    }
}
