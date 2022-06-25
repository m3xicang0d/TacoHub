package me.jesusmx.tacohub.listerners;

import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.m3xicang0d.api.listener.PluginListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@PluginListener
public class BorderListener implements Listener {

    FileConfiguration config = ConfigFile.getConfig();
    FileConfiguration toggle = ConfigFile.getConfig();

    @EventHandler
    public void BorderPlayer(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getBlockY() < 0) {
        }
        if (toggle.getBoolean("WORLD.BORDER.ENABLED")) {
            int X = event.getTo().getBlockX();
            int Z = event.getTo().getBlockZ();
            int xMax = config.getInt("WORLD.BORDER.MAX-X");
            int zMax = config.getInt("WORLD.BORDER.MAX-Z");
            String message = CC.translate(ConfigFile.getConfig().getString("WORLD.BORDER.MESSAGE"));
            if (X >= xMax) {
                player.teleport(event.getFrom());
                player.sendMessage(CC.translate(message));
            }
            if (Z >= zMax) {
                player.teleport(event.getFrom());
                player.sendMessage(CC.translate(message));
            }
            if (X <= -xMax) {
                player.teleport(event.getFrom());
                player.sendMessage(CC.translate(message));
            }
            if (Z <= -zMax) {
                player.teleport(event.getFrom());
                player.sendMessage(CC.translate(message));
            }
        }
    }
}
