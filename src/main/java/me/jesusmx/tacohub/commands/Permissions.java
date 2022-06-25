package me.jesusmx.tacohub.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public enum Permissions {
    BUILDER_MODE_COMMAND("COMMAND.BUILDER_MODE"),
    SET_SPAWN_COMMAND("COMMAND.SETSPAWN"),
    RESTRICTED_HUB("HUBCORE.RESTRICTED_HUB");

    private String permission;

    public boolean has(Player player) {
        return player.hasPermission(permission);
    }
}
