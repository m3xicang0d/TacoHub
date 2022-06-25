package me.jesusmx.tacohub.listerners;

import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.m3xicang0d.api.listener.PluginListener;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class LaunchPadListener implements Listener {

    @EventHandler
    public void onLaunchEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!ConfigFile.getConfig().getBoolean("LAUNCH_PAD.ENABLED")) return;
        if (player.getLocation().getBlock().getType() == Material.valueOf(ConfigFile.getConfig().getString("LAUNCH_PAD.MATERIAL"))) {
            player.setVelocity(player.getLocation().getDirection().multiply(2.0).setY(1.0));
            player.playSound(player.getLocation(), Sound.valueOf(ConfigFile.getConfig().getString("LAUNCH_PAD.SOUND")), 2.0f, 2.0f);
        }
    }
}
