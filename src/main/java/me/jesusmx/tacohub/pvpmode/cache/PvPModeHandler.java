package me.jesusmx.tacohub.pvpmode.cache;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class PvPModeHandler {

    private static List<UUID> inPvPMode;

    public PvPModeHandler() {
        inPvPMode = new ArrayList<>();
    }

    public static boolean isOnPvPMode(Player player) {
        return inPvPMode.contains(player.getUniqueId());
    }

    public static void togglePvPMode(Player player) {
        if(isOnPvPMode(player)) {
            inPvPMode.remove(player.getUniqueId());
        } else {
            inPvPMode.add(player.getUniqueId());
        }
    }
}