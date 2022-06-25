package me.jesusmx.tacohub.pvpmode.listener;

import io.github.m3xicang0d.api.listener.PluginListener;
import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

@PluginListener(debug = true)
public class PvPModeListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
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

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if(!PvPModeHandler.isOnPvPMode(player)) return;
        event.setCancelled(false);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFoodChange(FoodLevelChangeEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if(!PvPModeHandler.isOnPvPMode(player)) return;
        event.setCancelled(false);
    }

    @SuppressWarnings("ConstantConditions")
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
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