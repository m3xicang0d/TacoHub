package me.jesusmx.tacohub.listerners;

import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class DoubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE) return;
        if(PvPModeHandler.isOnPvPMode(player)) return;
        if (ConfigFile.getConfig().getBoolean("DOUBLE-JUMP.ENABLED"))
            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(ConfigFile.getConfig().getDouble("DOUBLE-JUMP.VELOCITY")).setY(1));
        player.playSound(player.getLocation(), Sound.valueOf(ConfigFile.getConfig().getString("DOUBLE-JUMP.SOUND").toUpperCase()), 1.0F, 1.0F);
        player.getWorld().spigot().playEffect(player.getLocation(), Effect.valueOf(ConfigFile.getConfig().getString("DOUBLE-JUMP.EFFECT").toUpperCase()), 26, 0, 0.2F, 0.5F, 0.2F, 0.2F, 12, 387);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(PvPModeHandler.isOnPvPMode(player)) return;
        if(player.getGameMode() == GameMode.CREATIVE) return;
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            player.setAllowFlight(true);
        }
    }
}
