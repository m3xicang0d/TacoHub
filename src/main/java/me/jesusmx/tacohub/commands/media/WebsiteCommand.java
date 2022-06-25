package me.jesusmx.tacohub.commands.media;

import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.MessagesFile;
import org.bukkit.command.CommandSender;

public class WebsiteCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (MessagesFile.getConfig().getBoolean("MESSAGES.WEBSITE-COMMAND.ENABLED")) {
            MessagesFile.getConfig().getStringList("MESSAGES.WEBSITE-COMMAND.MESSAGE").stream().map(CC::translate).forEach(sender::sendMessage);
        }
        return true;
    }
}

