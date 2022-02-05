package me.jesusmx.tacohub.permissions.type;

import com.broustudio.MizuAPI.MizuAPI;
import me.jesusmx.tacohub.permissions.PermissionCore;
import org.bukkit.entity.Player;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class MizuPermissionCore implements PermissionCore {
    @Override
    public String getRankColor(Player player) {
        return MizuAPI.getAPI().getRankColor(MizuAPI.getAPI().getRank(player.getUniqueId()));
    }

    @Override
    public String getRank(Player player) {
        return MizuAPI.getAPI().getRank(player.getUniqueId());
    }

}
