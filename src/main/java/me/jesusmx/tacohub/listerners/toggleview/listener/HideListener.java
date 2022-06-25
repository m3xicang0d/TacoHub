package me.jesusmx.tacohub.listerners.toggleview.listener;

import me.jesusmx.tacohub.TacoHub;
import io.github.m3xicang0d.api.listener.PluginListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

@PluginListener
public class HideListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setMetadata("PLAYERS_SHOWEDS", new FixedMetadataValue(TacoHub.getInstance(), true));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("PLAYERS_SHOWEDS")) player.removeMetadata("PLAYERS_SHOWEDS", TacoHub.getInstance());
        if(player.hasMetadata("PLAYERS_HIDED")) player.removeMetadata("PLAYERS_HIDED", TacoHub.getInstance());
    }
}
