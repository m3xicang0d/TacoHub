package me.jesusmx.tacohub.listerners;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.commands.Permissions;
import me.jesusmx.tacohub.cosmetics.item.CosmeticsItem;
import me.jesusmx.tacohub.listerners.enderbutt.item.EnderButtItem;
import me.jesusmx.tacohub.parkour.item.ParkourItem;
import me.jesusmx.tacohub.listerners.rocket.RocketItem;
import me.jesusmx.tacohub.pvpmode.item.PvPModeItem;
import me.jesusmx.tacohub.selector.hub.item.HubSelectorItem;
import me.jesusmx.tacohub.selector.server.item.ServerSelectorItem;
import me.jesusmx.tacohub.listerners.toggleview.item.ShowedItem;
import me.jesusmx.tacohub.utils.bungee.BungeeUtils;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.m3xicang0d.api.listener.PluginListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

@PluginListener
public class ItemsListener implements Listener {

    private final FileConfiguration config = HotbarFile.getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                // Restricted hub, no is a item but xd
                if (ConfigFile.getConfig().getBoolean("RESTRICTED-HUB.ENABLED")) {
                    if (Permissions.RESTRICTED_HUB.has(player)) {
                        String server = ConfigFile.getConfig().getString("RESTRICTED-HUB.BUNGEE-NAME");
                        BungeeUtils.sendToServer(player, server);
                    }
                }
                // Server selector item
                if(config.getBoolean("SERVER-SELECTOR.ENABLED")) {
                    new ServerSelectorItem().set(player, config.getInt("SERVER-SELECTOR.SLOT"));
                }
                // Hub selector item
                if(config.getBoolean("HUB-SELECTOR.ENABLED")) {
                    new HubSelectorItem().set(player, config.getInt("HUB-SELECTOR.SLOT"));
                }
                // Parkour mode item
                if (config.getBoolean("PARKOUR.ENABLED")) {
                    new ParkourItem().set(player, config.getInt("PARKOUR.SLOT"));
                }
                // Enderbutt item
                if(HotbarFile.getConfig().getBoolean("ENDERBUTT.ENABLED")) {
                    new EnderButtItem().set(player, config.getInt("ENDERBUTT.SLOT"));
                }
                // Rocket item
                if(config.getBoolean("ROCKET.ENABLED")) {
                    new RocketItem().set(player, config.getInt("ROCKET.SLOT"));
                }
                // Player hider/shower item
                if(config.getBoolean("PLAYER_HIDER.ENABLED")) {
                    new ShowedItem().set(player, config.getInt("PLAYER_HIDER.SLOT"));
                }
                // Cosmetics Item
                if (config.getBoolean("COSMETICS.ENABLED")) {
                    new CosmeticsItem().set(player, config.getInt("COSMETICS.SLOT"));
                }

                if (config.getBoolean("PVP_MODE.ENABLED")) {
                    new PvPModeItem().set(player, config.getInt("PVP_MODE.SLOT"));
                }

            }
        }.runTaskLater(TacoHub.getInstance(), 5);
    }
}