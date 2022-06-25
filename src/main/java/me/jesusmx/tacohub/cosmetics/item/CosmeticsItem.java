package me.jesusmx.tacohub.cosmetics.item;

import me.jesusmx.tacohub.cosmetics.menu.CosmeticsMenu;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.m3xicang0d.api.item.Item;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

public class CosmeticsItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("COSMETICS.MATERIAL")))
                .name(config.getString("COSMETICS.NAME"))
                .lore(config.getStringList("COSMETICS.LORE"))
                .data(config.getInt("COSMETICS.DATA"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        new CosmeticsMenu().openMenu(event.getPlayer());
    }
}
