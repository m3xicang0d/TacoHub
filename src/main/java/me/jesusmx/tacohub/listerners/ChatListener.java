package me.jesusmx.tacohub.listerners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class ChatListener implements Listener {

   private boolean isLunarClient(Player player) {
       return LunarClientAPI.getInstance().isRunningLunarClient(player);
   }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (ConfigFile.getConfig().getBoolean("CHAT-FORMAT.MUTED.ENABLED")) {
            if(event.getPlayer().hasPermission("hubcore.chat-bypass")) return;
            event.getPlayer().sendMessage(CC.translate(ConfigFile.getConfig().getString("CHAT-FORMAT.MUTED.MESSAGE")));
            event.setCancelled(true);
        } else if (ConfigFile.getConfig().getBoolean("CHAT-FORMAT.NORMAL.ENABLED")) {
            String msg = ChatColor.translateAlternateColorCodes('&',
                    ConfigFile.getConfig().getString("CHAT-FORMAT.NORMAL.MESSAGE")
                            .replace("%player%", player.getName())
                            .replace("%message%", event.getMessage())
                            .replace("%suffix%", TacoHub.chat.getPlayerSuffix(player))
                            .replace("%prefix%", TacoHub.chat.getPlayerPrefix(player)));
            if(Bukkit.getPluginManager().getPlugin("LunarClient-API") != null && Bukkit.getPluginManager().getPlugin("LunarClient-API").isEnabled()) {
                msg = ChatColor.translateAlternateColorCodes('&',
                        ConfigFile.getConfig().getString("CHAT-FORMAT.NORMAL.MESSAGE")
                                .replace("%player%", player.getName())
                                .replace("%lunar%", CC.translate(isLunarClient(player) ? ConfigFile.getConfig().getString("CHAT-FORMAT.NORMAL.PREFIX-LUNAR") : ""))
                                .replace("%message%", event.getMessage())
                                .replace("%suffix%", TacoHub.chat.getPlayerSuffix(player))
                                .replace("%prefix%", TacoHub.chat.getPlayerPrefix(player)));
            }
            event.setCancelled(true);
            String finalMsg = msg;
            TacoHub.getInstance().getOnlinePlayers().forEach(p -> p.sendMessage(finalMsg));
        }
    }
}
