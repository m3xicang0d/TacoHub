package me.jesusmx.tacohub.pvpmode.command;

import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class PvPModeCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(CC.NO_CONSOLE);
            return false;
        }
        Player player = (Player) sender;
        PvPModeHandler.togglePvPMode(player);
        return true;
    }
}