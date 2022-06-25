package io.github.m3xicang0d.api.listener;

import io.github.m3xicang0d.api.cf.ClassFinder;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenersHandler {

    public void registerAll() {
        for(Class<? extends Listener> lClass : ClassFinder.findAll(Listener.class)) {
            PluginListener listener = lClass.getAnnotation(PluginListener.class);
            if(listener == null) continue;
            try {
                Bukkit.getPluginManager().registerEvents(lClass.newInstance(), TacoHub.getInstance());
                if(listener.debug()) {
                    Bukkit.getConsoleSender().sendMessage(CC.GREEN + "Successfully registered the listener: " + lClass.getName());
                }
            } catch (Exception e) {
                if(listener.debug()) {
                    Bukkit.getConsoleSender().sendMessage(CC.RED + "Error registering the listener: " + lClass.getName());
                    e.printStackTrace();
                }
            }
        }
    }
}
