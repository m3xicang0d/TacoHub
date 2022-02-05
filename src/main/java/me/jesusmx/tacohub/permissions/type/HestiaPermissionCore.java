package me.jesusmx.tacohub.permissions.type;

import me.jesusmx.tacohub.permissions.PermissionCore;
import me.quartz.hestia.HestiaAPI;
import org.bukkit.entity.Player;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class HestiaPermissionCore implements PermissionCore {

    @Override
    public String getRankColor(Player player) {
        return HestiaAPI.instance.getRankColor(player.getUniqueId()).toString();
    }

    @Override
    public String getRank(Player player) {
        return HestiaAPI.instance.getRank(player.getUniqueId());
    }
}
