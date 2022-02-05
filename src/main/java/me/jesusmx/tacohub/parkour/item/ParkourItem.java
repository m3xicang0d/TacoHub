package me.jesusmx.tacohub.parkour.item;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.fxmxgragfx.api.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ParkourItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("PARKOUR.MATERIAL")))
                .name(config.getString("PARKOUR.NAME"))
                .lore(config.getStringList("PARKOUR.LORE"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        int x = config.getInt("PARKOUR.PARKOUR.X");
        int y = config.getInt("PARKOUR.PARKOUR.Y");
        int z = config.getInt("PARKOUR.PARKOUR.Z");
        player.teleport(new Location(Bukkit.getWorld(config.getString("PARKOUR.PARKOUR.WORLD")), x, y, z));
        player.setMetadata("PARKOUR", new FixedMetadataValue(TacoHub.getInstance(), System.currentTimeMillis()));
    }
}