package me.jesusmx.tacohub.commands.queue;

import me.jesusmx.tacohub.queue.custom.QueueHandler;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveQueueCommand extends Command {

   @Override
   public boolean execute(CommandSender sender, String label, String[] args) {
      if(!(sender instanceof Player)) {
         sender.sendMessage(CC.NO_CONSOLE);
         return false;
      }
      Player player = (Player)sender;
      if (QueueHandler.getQueue(player) == null) {
         player.sendMessage(CC.translate("&cYou aren't in the queue!"));
      } else {
         player.sendMessage(CC.translate(ConfigFile.getConfig().getString("QUEUE_MESSAGES.LEAVE_QUEUE"))
                 .replaceAll("%server%", QueueHandler.getQueueName(player)));
         QueueHandler.getQueue(player).removeEntry(player);
      }
      return false;
   }
}
