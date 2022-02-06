package me.jesusmx.tacohub.commands.impl.media;

import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.MessagesFile;
import org.bukkit.command.CommandSender;

public class HelpCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (MessagesFile.getConfig().getBoolean("MESSAGES.HELP-COMMAND.ENABLED")) {
            MessagesFile.getConfig().getStringList("MESSAGES.HELP-COMMAND.MESSAGE").stream().map(CC::translate).forEach(sender::sendMessage);
        }
        return true;
    }
}
