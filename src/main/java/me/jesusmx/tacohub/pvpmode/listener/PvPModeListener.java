package me.jesusmx.tacohub.pvpmode.listener;

import io.github.fxmxgragfx.api.listener.PluginListener;
import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * @Author UKry
 * @Project TacoHub
 **/

@PluginListener
public class PvPModeListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDamager() instanceof Player)) return;
        Player damaged = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if(!PvPModeHandler.isOnPvPMode(damager)) return;
        if(!PvPModeHandler.isOnPvPMode(damaged)) return;
        event.setCancelled(false);
    }

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(event.getEntity().getKiller() == null) return;
        if(!(event.getEntity().getKiller() instanceof Player)) return;
        event.getDrops().clear();
        Player killed = (Player) event.getEntity();
        Player killer = killed.getKiller();
        PvPModeHandler.getKills().putIfAbsent(killer.getUniqueId(), 0);
        int kills = PvPModeHandler.getKills().get(killer.getUniqueId()) + 1;
        PvPModeHandler.getKills().replace(killer.getUniqueId(), kills);
        PvPModeHandler.togglePvPMode(killed);
    }
}