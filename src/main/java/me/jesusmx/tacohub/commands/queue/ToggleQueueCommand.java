package me.jesusmx.tacohub.commands.queue;

import me.jesusmx.tacohub.queue.custom.QueueData;
import me.jesusmx.tacohub.queue.custom.QueueHandler;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.command.CommandSender;


public class ToggleQueueCommand extends Command {

   @Override
   public boolean execute(CommandSender sender, String label, String[] args) {
      if (args.length == 0) {
         sender.sendMessage(CC.translate("&cUsage: /togglequeue <queue>"));
      } else {
         String server = args[0];
         if (QueueHandler.getQueue(server) == null) {
            sender.sendMessage(CC.translate("&cQueue named " + server + " doesn't exists!"));
            return false;
         }

         QueueData queue = QueueHandler.getQueue(server);
         if (queue.isPaused()) {
            sender.sendMessage(CC.translate(ConfigFile.getConfig().getString("QUEUE_MESSAGES.UNPAUSED"))
                    .replaceAll("%server%", queue.getServer()));
         } else {
            sender.sendMessage(CC.translate(ConfigFile.getConfig().getString("QUEUE_MESSAGES.PAUSED"))
                    .replaceAll("%server%", queue.getServer()));
         }

         queue.setPaused(!queue.isPaused());
      }
      return false;
   }
}
