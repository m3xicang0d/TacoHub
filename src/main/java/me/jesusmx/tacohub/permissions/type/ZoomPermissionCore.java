package me.jesusmx.tacohub.permissions.type;

import club.frozed.core.ZoomAPI;
import me.jesusmx.tacohub.permissions.PermissionCore;
import org.bukkit.entity.Player;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class ZoomPermissionCore implements PermissionCore {

    @Override
    public String getRankColor(Player player) {
        return null;
    }

    @Override
    public String getRank(Player player) {
        return ZoomAPI.getRankName(player);
    }
}
