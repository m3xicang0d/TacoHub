package me.jesusmx.tacohub.provider;

import io.github.nosequel.tab.shared.entry.TabElement;
import io.github.nosequel.tab.shared.entry.TabElementHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.hooker.Hooker;
import me.jesusmx.tacohub.hooker.Splitters;
import me.jesusmx.tacohub.utils.bungee.BungeeUtils;
import me.jesusmx.tacohub.queue.QueueManager;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.features.HookerFile;
import me.jesusmx.tacohub.utils.files.features.TablistFile;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class TablistProvider implements TabElementHandler {

    FileConfiguration config = TablistFile.getConfig();

    @Override
    public TabElement getElement(Player player) {
        TabElement element = new TabElement();
        element.setHeader(CC.translate(config.getString("TABLIST.HEADER").replace("<line>", "\n")));
        element.setFooter(CC.translate(config.getString("TABLIST.FOOTER").replace("<line>", "\n")));
        List list = Arrays.asList((Object[])new String[]{"LEFT", "MIDDLE", "RIGHT", "FAR-RIGHT"});
        for (int i = 0; i < 4; ++i) {
            String s = (String)list.get(i);
            for (int l = 0; l < 20; ++l) {
                String str = PlaceholderAPI.setPlaceholders(player, config.getString(String.valueOf(new StringBuilder().append("TABLIST.").append(s).append(".").append(l + 1)))
                                .replace("%player%", player.getDisplayName())
                                .replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .replace("%online%", String.valueOf(BungeeUtils.getGlobalPlayers()))
                        .replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player));
                QueueManager queues = TacoHub.getInstance().getQueueManager();
                String queue = CC.translate(config.getString("TABLIST.QUEUE.SERVER"));
                String position = CC.translate(config.getString("TABLIST.QUEUE.POSITION"));
                String inQueue = CC.translate(config.getString("TABLIST.QUEUE.IN-QUEUE"));
                if(queues.inQueue(player)) {
                    queue = queues.getQueueIn(player);
                    position = String.valueOf(queues.getPosition(player));
                    inQueue = String.valueOf(queues.getInQueue(queue));
                }
                str = str.replace("%queue_server%", queue);
                str = str.replace("%queue_position%", position);
                str = str.replace("%queue_size%", inQueue);
                if(HookerFile.getConfig().getBoolean("ENABLED")) {
                    if(!Hooker.getVerified().isEmpty()) {
                        for (String sk : Hooker.getVerified()) {
                            String path = "VARIABLES." + sk;
                            String host = HookerFile.getConfig().getString(path + ".HOST");
                            int port = HookerFile.getConfig().getInt(path + ".PORT");
                            try {
                                Socket socket = new Socket(host, port);
                                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                                DataInputStream dis = new DataInputStream(socket.getInputStream());
                                dos.writeUTF(sk + Splitters.REQUEST + player.getUniqueId());
                                dos.flush();
                                String response = dis.readUTF();
                                String[] rs = response.split(Splitters.REQUEST);
                                str = str.replace("%" + sk + "_LIVES%", rs[1]);
                                str = str.replace("%" + sk + "_DEATHBAN%", rs[2]);
                                dos.close();
                                dis.close();
                                socket.close();
                            } catch (IOException e) {
                                str = str.replace("%" + sk + "_LIVES%", "0");
                                str = str.replace("%" + sk + "_DEATHBAN%", "Loading");
                                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The connection with the hook " + sk + " has been lost");
                                Hooker.getVerified().remove(sk);
                                Hooker.getUnverified().add(sk);
                            }
                        }
                    }
                    for(String sk : Hooker.getUnverified()) {
                        str = str.replace("%" + sk + "_LIVES%", "0");
                        str = str.replace("%" + sk + "_DEATHBAN%", "Loading");
                    }
                }
                element.add(i, l, str);
            }
        }
        return element;
    }
}