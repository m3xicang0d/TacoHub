package me.jesusmx.tacohub.cosmetics.command;

import me.jesusmx.tacohub.cosmetics.menu.CosmeticsMenu;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

public class CosmeticsCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(CC.NO_CONSOLE);
            return false;
        }
        new CosmeticsMenu().openMenu((Player) sender);
        return false;
    }
}
