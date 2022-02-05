package me.jesusmx.tacohub.listerners;

import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class ProtectionListener implements Listener {

    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!ConfigFile.getConfig().getBoolean("PROTECTION.ENABLED")) return;
        String command = event.getMessage().split(" ")[0];
        List<String> blockedMessage = ConfigFile.getConfig().getStringList("PROTECTION.BLOCKED-COMMANDS");
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        if (blockedMessage.contains(command.toLowerCase())) {
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("PROTECTION.MESSAGE")));
            event.setCancelled(true);
        }

    }
}
