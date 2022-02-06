package me.jesusmx.tacohub.listerners.enderbutt.listener;

import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class EnderButtListener implements Listener {

    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        event.getDismounted().remove();
    }
}
