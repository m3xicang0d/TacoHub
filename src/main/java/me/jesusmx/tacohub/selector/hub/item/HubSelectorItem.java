package me.jesusmx.tacohub.selector.hub.item;

import me.jesusmx.tacohub.selector.hub.menu.HubSelectorMenu;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.m3xicang0d.api.item.Item;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HubSelectorItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("HUB-SELECTOR.MATERIAL")))
                .name(config.getString("HUB-SELECTOR.NAME"))
                .lore(config.getStringList("HUB-SELECTOR.LORE"))
                .data(config.getInt("HUB-SELECTOR.DATA"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        new HubSelectorMenu().openMenu(event.getPlayer());
    }
}
