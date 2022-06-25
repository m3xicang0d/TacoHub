package me.jesusmx.tacohub.queue.custom.listener;

import me.jesusmx.tacohub.queue.custom.QueueData;
import me.jesusmx.tacohub.queue.custom.QueueHandler;
import io.github.m3xicang0d.api.listener.PluginListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@PluginListener
public class QueueListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for(QueueData queue : QueueHandler.queues) {
            if (queue.getPlayers().contains(player)) {
                queue.removeEntry(player);
            }
        }

    }
}
