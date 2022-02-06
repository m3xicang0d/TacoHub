package me.jesusmx.tacohub.listerners.lunar;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCListener {

    private FileConfiguration config = ConfigFile.getConfig();

    public LCListener() {
        if (!config.getBoolean("LunarClientAPI.Enabled")) {
            return;
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(TacoHub.getInstance(), () -> {
            for (Player player : TacoHub.getInstance().getOnlinePlayers()) {
                int radiusX = 60;
                int radiusY = 60;
                int radiusZ = 60;
                List<Entity> entityList = player.getNearbyEntities(radiusX, radiusY, radiusZ);
                for (Entity en : entityList) {
                    if (en instanceof Player) {
                        Player other = (Player) en;
                        if (player.getUniqueId() == other.getUniqueId())
                            continue;
                        if (config.getBoolean("LunarClientAPI.Above-Head.Enabled"))
                            LunarClientAPI.getInstance().overrideNametag(other, Arrays.asList(fetchNametag(other, player).toArray(new String[0])), player);
                    }
                }
            }
        }, 0L, 15L);
    }
    public List<String> fetchNametag(Player target, Player viewer) {
        List<String> tag = new ArrayList<>();

        // If we already found something above they override these, otherwise we can do these checks.
        String realTag = config.getString("LunarClientAPI.Above-Head.Format-Nametag");
        String formatstaff = config.getString("LunarClientAPI.Above-Head.Staff-Tag.Format");
        String perm = config.getString("LunarClientAPI.Above-Head.Staff-Tag.Permission");
        boolean enabled = config.getBoolean("LunarClientAPI.Above-Head.Staff-Tag.Enabled");

        if (enabled) {
            if (target.hasPermission(perm)) {
                tag.add(CC.translate(formatstaff.replaceAll("%prefix%", TacoHub.chat.getPlayerPrefix(target))));
                tag.add(CC.translate(realTag.replaceAll("%name%", target.getName())));
                return tag;
            }
        }
        String format = config.getString("LunarClientAPI.Above-Head.Format");
        tag.add(CC.translate(format.replaceAll("%prefix%", TacoHub.chat.getPlayerPrefix(target))));
        tag.add(CC.translate(realTag.replaceAll("%name%", target.getName())));
        return tag;
    }
}
