package me.jesusmx.tacohub.commands.queue;

import me.jesusmx.tacohub.queue.custom.QueueData;
import me.jesusmx.tacohub.queue.custom.QueueHandler;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinQueueCommand extends Command {

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(CC.NO_CONSOLE);
			return false;
		}
		Player player = (Player) sender;
		if(args.length != 1) {
			sender.sendMessage(CC.translate("&c/play <queuename>"));
		} else {
			QueueData queue = QueueHandler.getQueue(args[0]);
			if(queue == null) {
				player.sendMessage(CC.translate("&cThe queue " + args[0] + " not exists"));
			} else {
				if(queue.isPaused()) {
					player.sendMessage(CC.translate(ConfigFile.getConfig().getString("QUEUE_MESSAGES.PAUSED")
							.replace("%server%", queue.getServer())));
				} else {
					queue.addEntry(player);
				}
			}
		}
		return true;
	}
}
