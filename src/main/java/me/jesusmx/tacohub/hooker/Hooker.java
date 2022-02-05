package me.jesusmx.tacohub.hooker;

import lombok.Getter;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.features.HookerFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class Hooker {

    @Getter private static final Set<String> unverified = new HashSet<>();
    @Getter private static final Set<String> verified = new HashSet<>();
    protected static ServerSocket server;
    protected static int port;

    public Hooker() {
        try {
            FileConfiguration config = HookerFile.getConfig();
            unverified.addAll(config.getConfigurationSection("VARIABLES").getKeys(false));
            port = config.getInt("PORT");
            server = new ServerSocket(port);
            Bukkit.getScheduler().runTaskLaterAsynchronously(TacoHub.getInstance(), () -> Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&6TacoHub&8&8] &aHCF-Hooks: Checking " + unverified.size() + " hooks")), 100L);
            List<String> toRemove = new ArrayList<>();
            List<String> toAdd = new ArrayList<>();
            for(String s : Hooker.getUnverified()) {
                String path = "VARIABLES." + s;
                String host =  config.getString(path + ".HOST");
                int port = config.getInt(path + ".PORT");
                try {
                    Socket socket = new Socket(host, port);
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(Splitters.REQUEST + "ENABLED" + Splitters.REQUEST + s);
                    dos.flush();
                    String msg = dis.readUTF();
                    if(msg.contains("SUCCESSFULLY")) {
                        toAdd.add(s);
                        toRemove.add(s);
                    }
                    dis.close();
                    dos.close();
                    socket.close();

                } catch (IOException ignored) {}
            }
            toRemove.forEach(Hooker.getUnverified()::remove);
            Hooker.getVerified().addAll(toAdd);
            new HookReceiverThread().start();
            Bukkit.getScheduler().runTaskLaterAsynchronously(TacoHub.getInstance(), () -> Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&6TacoHub&8&8] &aHCF-Hooks: Verified " + ChatColor.GREEN + verified.size() + " hooks, You have " + ChatColor.GREEN + unverified.size() + " &aunverified hooks!")), 101L);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("&8[&6TacoHub&8&8] &aHCF-Hooks: &cError initializing the hooker");
            if(HookerFile.getConfig().getBoolean("DEBUG")) e.printStackTrace();
        }
    }
}
