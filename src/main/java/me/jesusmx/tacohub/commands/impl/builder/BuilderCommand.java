package me.jesusmx.tacohub.commands.impl.builder;

import me.jesusmx.tacohub.commands.impl.Permissions;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import me.jesusmx.tacohub.utils.files.normal.MessagesFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuilderCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(CC.NO_CONSOLE);
            return false;
        }
        Player player = (Player) sender;
        if(Permissions.BUILDER_MODE_COMMAND.has(player)){
            if (ConfigFile.getConfig().getBoolean("WORLD.BUILDER-MODE")) {
                if (BuilderMode.getBuildMode(player)) {
                    player.sendMessage(CC.translate(MessagesFile.getConfig().getString("BUILDER-MODE.DISABLED")));
                    BuilderMode.removePlayer(player);
                } else {
                    player.sendMessage(CC.translate(MessagesFile.getConfig().getString("BUILDER-MODE.ENABLED")));
                    BuilderMode.addPlayer(player);
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED +"You do not have permission to use this command.");
        }
        return true;
    }
}
