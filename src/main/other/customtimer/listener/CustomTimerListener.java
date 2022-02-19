package me.jesusmx.tacohub.customtimer.listener;

import io.github.fxmxgragfx.api.listener.PluginListener;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.customtimer.command.CustomTimer;
import me.jesusmx.tacohub.customtimer.command.CustomTimerCache;
import me.jesusmx.tacohub.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * @Author UKry
 * @Project TacoHub
 **/

@PluginListener(debug = true)
public class CustomTimerListener implements Listener {

    @EventHandler
    public void setTimerText(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("TIMER_CONFIRMATION")) return;
        if(!player.hasMetadata("MAKING_TIMER")) return;
        event.setCancelled(true);
        CustomTimer timer = (CustomTimer) player.getMetadata("MAKING_TIMER").get(0).value();
        timer.setText(event.getMessage());
        player.sendMessage(CC.GRAY + "You want to change the text of the customtimer 'id' to: " + event.getMessage());
        player.sendMessage(CC.GREEN + "Type 'yes' to confirm");
        player.sendMessage(CC.RED + "Type 'no' to re-enter the text");
        player.removeMetadata("MAKING_TIMER", TacoHub.getInstance());
        player.setMetadata("TIMER_CONFIRMATION", new FixedMetadataValue(TacoHub.getInstance(), timer));
    }

    @EventHandler
    public void confirmText(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("MAKING_TIMER")) return;
        if(!player.hasMetadata("TIMER_CONFIRMATION")) return;
        event.setCancelled(true);
        if(event.getMessage().equalsIgnoreCase("yes")) {
            CustomTimer timer = (CustomTimer) player.getMetadata("TIMER_CONFIRMATION").get(0).value();
            CustomTimerCache.getTimers().replace(timer.getId(), timer);
            player.removeMetadata("MAKING_TIMER", TacoHub.getInstance());
            player.removeMetadata("TIMER_CONFIRMATION", TacoHub.getInstance());
            player.sendMessage(CC.GREEN + "Successfully changed the text of the timer " + timer.getId());
        } else if(event.getMessage().equalsIgnoreCase("no")) {

        } else {

        }
    }
}
