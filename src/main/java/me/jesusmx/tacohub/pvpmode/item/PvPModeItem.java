package me.jesusmx.tacohub.pvpmode.item;

import io.github.fxmxgragfx.api.item.Item;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class PvPModeItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("PVP_MODE.MATERIAL")))
                .data(config.getInt("PVP_MODE.DATA"))
                .name(config.getString("PVP_MODE.NAME"))
                .lore(config.getStringList("PVP_MODE.LORE"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        PvPModeHandler.togglePvPMode(event.getPlayer());
    }
}