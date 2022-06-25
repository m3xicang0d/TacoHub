package me.jesusmx.tacohub.pvpmode.command;

import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import me.jesusmx.tacohub.pvpmode.config.PvPModeFile;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author JesusMX
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
        if(args.length > 0 && player.isOp()) {
            if(args[0].equalsIgnoreCase("setinv")) {
                PvPModeFile.getConfig().set("INVENTORY", player.getInventory().getContents());
                PvPModeFile.getConfig().set("ARMOR", player.getInventory().getArmorContents());
                PvPModeFile.getConfig().save();
                PvPModeFile.getConfig().reload();
                player.sendMessage(CC.GREEN + "Successfully updated the pvpmode inventory!");
            }
        } else {
            PvPModeHandler.togglePvPMode(player);
        }
        return true;
    }
}