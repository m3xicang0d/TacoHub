package me.jesusmx.tacohub.listerners;

import io.github.m3xicang0d.api.listener.PluginListener;
import lombok.Getter;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.license.TacoLicense;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@PluginListener @Getter
public class DeveloperListener implements Listener {

    @EventHandler
    public void Owner(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equals("BestHonduras")) {
            player.sendMessage(CC.translate("&7&m----------------------------------------"));
            player.sendMessage(CC.translate("&6This server is using TacoHub"));
            player.sendMessage(CC.translate("&fLicense is: " + TacoLicense.productKey));
            player.sendMessage(CC.translate("&7&m----------------------------------------"));
        }
    }

    @EventHandler
    public void Developer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equals("Tewels")) {
            player.sendMessage(CC.translate("&7&m----------------------------------------"));
            player.sendMessage(CC.translate("&6This server is using TacoHub"));
            player.sendMessage(CC.translate("&fLicense is: " + TacoLicense.productKey));
            player.sendMessage(CC.translate("&7&m----------------------------------------"));
        }
    }
}
