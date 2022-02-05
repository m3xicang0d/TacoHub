package me.jesusmx.tacohub.queue.custom;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.bungee.BungeeUtils;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "rawtypes"})
public class QueueHandler {
   public static List<QueueData> queues = new ArrayList();

   public QueueHandler() {
      for(String queue : ConfigFile.getConfig().getStringList("QUEUE_SERVERS")) {
         queues.add(new QueueData(queue));
      }
      (new BukkitRunnable() {
         public void run() {
            for(QueueData queue : QueueHandler.queues) {
               if (!queue.isPaused() && !queue.getPlayers().isEmpty() && QueueHandler.this.playerCount() < queue.getLimit()) {
                  queue.sendFirst();
                  queue.removeEntry(queue.getPlayerAt(0));
               }
            }

         }
      }).runTaskTimer(TacoHub.getInstance(), 30L, 30L);
   }

   public int playerCount() {
      for(QueueData queue : queues) {
         for(String queues : ConfigFile.getConfig().getStringList("QUEUE_SERVERS")) {
            if (queue.getServer().equalsIgnoreCase(queues)) {
               return BungeeUtils.getServersByName().get(queues).getPlayerCount();
            }
         }
      }
      return 0;
   }

   public static QueueData getQueue(Player player) {
      List<QueueData> queue = queues.stream().filter(q -> q.getPlayers().contains(player)).collect(Collectors.toList());
      if(queue.isEmpty()) return null;
      return queue.get(0);
   }

   public static QueueData getQueue(String server) {
      return queues.stream().filter(q -> q.getServer().equalsIgnoreCase(server)).collect(Collectors.toList()).get(0);
   }

   public static String getQueueName(Player player) {
      return getQueue(player).getServer();
   }

   public static int getPriority(Player player) {
      for(int i = 0; i < 10; i++) {
         if(player.hasPermission("QUEUE.PRIORITY." + i)) {
            return i;
         }
      }
      return 0;
   }

}
