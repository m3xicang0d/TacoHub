package me.jesusmx.tacohub.parkour.listener;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

@PluginListener
public class ParkourListener implements Listener {

    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(!player.hasMetadata("PARKOUR")) return;
        Location location = player.getLocation();
        location.setY(location.getY() - 1.0);
        if (location.getBlock().getType() != Material.valueOf(HotbarFile.getConfig().getString("PARKOUR.PARKOUR.BLOCK"))) {
            if (location.getBlock().getType() != Material.AIR) {
                int x = HotbarFile.getConfig().getInt("PARKOUR.SPAWN.X");
                int y = HotbarFile.getConfig().getInt("PARKOUR.SPAWN.Y");
                int z = HotbarFile.getConfig().getInt("PARKOUR.SPAWN.Z");
                player.removeMetadata("PARKOUR", TacoHub.getInstance());
                player.teleport(new Location(Bukkit.getWorld(HotbarFile.getConfig().getString("PARKOUR.SPAWN.WORLD")), x, y, z));
            } else {
                if(player.hasMetadata("PARKOUR")) return;
                player.setMetadata("PARKOUR", new FixedMetadataValue(TacoHub.getInstance(), true));
            }
        }
    }
}