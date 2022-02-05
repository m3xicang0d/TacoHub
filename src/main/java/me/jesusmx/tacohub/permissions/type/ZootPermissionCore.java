package me.jesusmx.tacohub.permissions.type;

import com.minexd.zoot.profile.Profile;
import me.jesusmx.tacohub.permissions.PermissionCore;
import org.bukkit.entity.Player;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class ZootPermissionCore implements PermissionCore {

    @Override
    public String getRankColor(Player player) {
        return Profile.getByUuid(player.getUniqueId()).getActiveGrant().getRank().getColor().name();
    }

    @Override
    public String getRank(Player p) {
        return Profile.getByUuid(p.getUniqueId()).getActiveGrant().getRank().getDisplayName();
    }

}
