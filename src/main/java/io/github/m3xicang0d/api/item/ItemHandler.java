package io.github.m3xicang0d.api.item;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.jesusmx.tacohub.TacoHub;
import io.github.m3xicang0d.api.cf.ClassFinder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemHandler {

    @SneakyThrows
    public void registerAll() {
        for(Class<? extends Item> iClass : ClassFinder.findAll(Item.class)) {
            Bukkit.getPluginManager().registerEvents(new ItemListener(iClass.newInstance()), TacoHub.getInstance());
        }
    }

    @AllArgsConstructor
    public class ItemListener implements Listener {
        private Item item;

        @EventHandler
        public void onPlayerInteract(PlayerInteractEvent event) {
            if(event.getPlayer().getItemInHand() == null) return;
            if(!event.getPlayer().getItemInHand().isSimilar(item.getItem())) return;
            item.onPlayerInteract(event);
        }
    }
}
