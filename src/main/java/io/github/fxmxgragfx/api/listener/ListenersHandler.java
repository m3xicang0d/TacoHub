package io.github.fxmxgragfx.api.listener;

import lombok.SneakyThrows;
import me.jesusmx.tacohub.TacoHub;
import io.github.fxmxgragfx.api.cf.ClassFinder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenersHandler {

    @SneakyThrows
    public void registerAll() {
        for(Class<? extends Listener> lClass : ClassFinder.findAll(Listener.class)) {
            if(lClass.getAnnotation(PluginListener.class) == null) continue;
            Bukkit.getPluginManager().registerEvents(lClass.newInstance(), TacoHub.getInstance());
        }
    }
}
