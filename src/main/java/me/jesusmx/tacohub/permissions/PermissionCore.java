package me.jesusmx.tacohub.permissions;

import org.bukkit.entity.Player;

public interface PermissionCore {

    String getRankColor(Player player);
    
    String getRank(Player player);
}
