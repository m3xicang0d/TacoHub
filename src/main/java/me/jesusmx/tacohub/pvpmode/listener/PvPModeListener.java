package me.jesusmx.tacohub.pvpmode.listener;

import io.github.fxmxgragfx.api.listener.PluginListener;
import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
        System.out.println(damager.getName());
        System.out.println(damaged.getName());
        if(!PvPModeHandler.isOnPvPMode(damager)) return;
        if(!PvPModeHandler.isOnPvPMode(damaged)) return;
        event.setCancelled(false);
    }

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;
        if(!(event.getEntity().getKiller() instanceof Player)) return;
        event.getDrops().clear();
        Player killed = event.getEntity();
        Player killer = killed.getKiller();
        PvPModeHandler.getKills().putIfAbsent(killer.getUniqueId(), 0);
        int kills = PvPModeHandler.getKills().get(killer.getUniqueId()) + 1;
        PvPModeHandler.getKills().replace(killer.getUniqueId(), kills);
        PvPModeHandler.togglePvPMode(killed);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(!PvPModeHandler.isOnPvPMode(event.getPlayer())) return;
        PvPModeHandler.togglePvPMode(event.getPlayer());
    }
}