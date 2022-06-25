package me.jesusmx.tacohub.commands.builder;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuilderMode {

    public static ArrayList<Player> build = new ArrayList<>();

    public static boolean getBuildMode(Player player) {
        return build.contains(player);
    }

    public static void addPlayer(Player player) {
        if (!build.contains(player)) {
            build.add(player);
        }
    }

    public static Integer getPlayers() {
        return build.size();
    }

    public static void removePlayer(Player player) {
        build.remove(player);
    }
}
