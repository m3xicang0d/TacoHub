package me.jesusmx.tacohub.listerners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class JoinPlayerListener implements Listener {


    @EventHandler
    public void ExitMessage(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (ConfigFile.getConfig().getBoolean("EXIT-ANNOUNCE.ENABLED")) {
            if (player.hasPermission("hubcore.donator")) {
                ConfigFile.getConfig().getStringList("EXIT-ANNOUNCE.MESSAGE").stream()
                        .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                        .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .map(line -> line.replace("%player%", player.getName()))
                        .map(line -> line.replace("%prefix%", TacoHub.chat.getPlayerPrefix(player)))
                        .forEach(m -> player.sendMessage(CC.translate(m)));
            }
        }
        event.setQuitMessage(null);
    }

    @EventHandler
    public void RegisterListeners(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        player.getInventory().clear();
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        FileConfiguration config = ConfigFile.getConfig();
        if (config.getBoolean("SPAWN.SET")) {
            Location location = new Location(
                    Bukkit.getWorld(config.getString("SPAWN.WORLD")),
                    config.getDouble("SPAWN.X"),
                    config.getDouble("SPAWN.Y"),
                    config.getDouble("SPAWN.Z"),
                    Float.parseFloat(config.getString("SPAWN.YAW")),
                    Float.parseFloat(config.getString("SPAWN.PITCH"))
            );
            player.teleport(location);
        } else {
            player.sendMessage(CC.translate("&cSpawn has not been set!"));
        }

        if (ConfigFile.getConfig().getBoolean("JOIN-MESSAGE.ENABLED")) {
            ConfigFile.getConfig().getStringList("JOIN-MESSAGE.LINES").stream()
                    .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                    .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                    .map(line -> line.replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player)))
                    .map(line -> line.replace("%player%", player.getName()))
                    .forEach(m -> player.sendMessage(CC.translate(m)));
        }

        if (ConfigFile.getConfig().getBoolean("JOIN-SOUND.ENABLE")) {
            player.playSound(player.getLocation(), Sound.valueOf(ConfigFile.getConfig().getString("JOIN-SOUND.SOUND").toUpperCase()), 1.0F, 1.0F);
        }

        if (ConfigFile.getConfig().getBoolean("JOIN-SPEED.ENABLE")) {
            player.setWalkSpeed((float) ConfigFile.getConfig().getDouble("JOIN-SPEED.MULTIPLIER"));
        } else {
            player.setWalkSpeed(0.2F);
        }

        if (ConfigFile.getConfig().getBoolean("JOIN-ANNOUNCE.ENABLED")) {
            if (player.hasPermission("hubcore.donator")) {
                ConfigFile.getConfig().getStringList("JOIN-ANNOUNCE.MESSAGE").stream()
                        .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                        .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .map(line -> line.replace("%player%", player.getName()))
                        .map(line -> line.replace("%prefix%", TacoHub.chat.getPlayerPrefix(player)))
                        .forEach(m -> player.sendMessage(CC.translate(m)));
            }
        }
        player.updateInventory();
    }
}