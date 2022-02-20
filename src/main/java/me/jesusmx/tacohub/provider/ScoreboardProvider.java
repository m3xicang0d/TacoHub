package me.jesusmx.tacohub.provider;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bungee.BungeeUtils;
import me.jesusmx.tacohub.utils.files.features.ScoreboardFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class ScoreboardProvider implements AssembleAdapter {

    private FileConfiguration config = ScoreboardFile.getConfig();
    private long lastMillisFooter = System.currentTimeMillis();
    private long lastMillisTitle = System.currentTimeMillis();
    private int iFooter = 0;
    private int iTitle = 0;

    @Override
    public String getTitle(Player player) {
        boolean enabled = config.getBoolean("SCOREBOARD.TITLE.ANIMATION.ENABLED");
        if (enabled) {
            return titles();
        } else {
            return CC.translate(config.getString("SCOREBOARD.TITLE.NORMAL"));
        }
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> toReturn;
        if (TacoHub.getInstance().getQueueManager().inQueue(player)) {
            if (player.hasMetadata("PARKOUR")) {
                toReturn = config.getStringList("SCOREBOARD.PARKOUR-QUEUE")
                        .stream()
                        .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                        .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .map(line -> line.replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player)))
                        .map(line -> line.replace("%server%", String.valueOf(TacoHub.getInstance().getQueueManager().getQueueIn(player))))
                        .map(line -> line.replace("%position%", String.valueOf(TacoHub.getInstance().getQueueManager().getPosition(player))))
                        .map(line -> line.replace("%online%", String.valueOf(BungeeUtils.getGlobalPlayers())))
                        .map(line -> line.replace("%duration%", String.valueOf((System.currentTimeMillis() - player.getMetadata("PARKOUR").get(0).asLong()) / 1000)))
                        .map(line -> line.replace("%size%", String.valueOf(TacoHub.getInstance().getQueueManager().getInQueue(TacoHub.getInstance().getQueueManager().getQueueIn(player)))))
                        .collect(Collectors.toList());
            } else {
                toReturn = config.getStringList("SCOREBOARD.QUEUED")
                        .stream()
                        .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                        .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .map(line -> line.replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player)))
                        .map(line -> line.replace("%server%", String.valueOf(TacoHub.getInstance().getQueueManager().getQueueIn(player))))
                        .map(line -> line.replace("%position%", String.valueOf(TacoHub.getInstance().getQueueManager().getPosition(player))))
                        .map(line -> line.replace("%online%", String.valueOf(BungeeUtils.getGlobalPlayers())))
                        .map(line -> line.replace("%size%", String.valueOf(TacoHub.getInstance().getQueueManager().getInQueue(TacoHub.getInstance().getQueueManager().getQueueIn(player)))))
                        .collect(Collectors.toList());
            }
        } else {
            if (player.hasMetadata("PARKOUR")) {
                toReturn = config.getStringList("SCOREBOARD.PARKOUR")
                        .stream()
                        .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                        .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .map(line -> line.replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player)))
                        .map(line -> line.replace("%duration%", String.valueOf((System.currentTimeMillis() - player.getMetadata("PARKOUR").get(0).asLong()) / 1000)))
                        .map(line -> line.replace("%online%", String.valueOf(BungeeUtils.getGlobalPlayers())))
                        .collect(Collectors.toList());
            } else {
                toReturn = config.getStringList("SCOREBOARD.NORMAL")
                        .stream()
                        .map(CC::translate)
                        .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                        .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                        .map(line -> line.replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player)))
                        .map(line -> line.replace("%online%", String.valueOf(BungeeUtils.getGlobalPlayers())))
                        .collect(Collectors.toList());
            }
        }
        if(PvPModeHandler.isOnPvPMode(player)) {
            toReturn = ScoreboardFile.getConfig().getStringList("SCOREBOARD.PVP-MODE")
                    .stream()
                    .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                    .map(line -> line.replace("%rank%", TacoHub.getInstance().getPermissionCore().getRank(player)))
                    .map(line -> line.replace("%rankcolor%", TacoHub.getInstance().getPermissionCore().getRankColor(player)))
                    .map(line -> line.replace("%server%", String.valueOf(TacoHub.getInstance().getQueueManager().getQueueIn(player))))
                    .map(line -> line.replace("%position%", String.valueOf(TacoHub.getInstance().getQueueManager().getPosition(player))))
                    .map(line -> line.replace("%online%", String.valueOf(BungeeUtils.getGlobalPlayers())))
                    .map(line -> line.replace("%kills%", String.valueOf(PvPModeHandler.getKills().getOrDefault(player.getUniqueId(), 0))))
                    .map(line -> line.replace("%duration%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - PvPModeHandler.getTime(player)))))
                    .map(line -> line.replace("%size%", String.valueOf(TacoHub.getInstance().getQueueManager().getInQueue(TacoHub.getInstance().getQueueManager().getQueueIn(player)))))
                    .collect(Collectors.toList());
        }
        if(config.getBoolean("SCOREBOARD.FOOTER.ANIMATION.ENABLED")) {
            String footer = footer();
            toReturn = toReturn.stream().map(s -> s.replace("%footer%", footer)).collect(Collectors.toList());
        }
        if(config.getBoolean("SCOREBOARD.TITLE.ANIMATION.ENABLED")) {
            String title = titles();
            toReturn = toReturn.stream().map(s -> s.replace("%TITLE%", title)).collect(Collectors.toList());
        }

       /* *//*Custom Timers Section*//*
        if(!CustomTimerCache.getActiveCustomTimers().isEmpty()) {
            for (CustomTimer timer : CustomTimerCache.getActiveCustomTimers()) {
                //Max size is 16
                if(!CustomTimerCache.getChars().containsKey(timer)) {
                    CustomTimerCache.getChars().put(timer, new CharProcessor(timer, 16, 250));
                }
                CharProcessor processor = CustomTimerCache.getChars().get(timer);
                processor.update();
                toReturn.add(processor.getFinalTextt());
            }
        }*/
        return toReturn;
    }


    private String footer() {
        List<String> footers = CC.translate(config.getStringList("SCOREBOARD.FOOTER.ANIMATION.ANIMATED"));
        long time = System.currentTimeMillis();
        long interval = TimeUnit.SECONDS.toMillis(config.getInt("SCOREBOARD.FOOTER.ANIMATION.INTERVAL"));

        if(lastMillisFooter + interval <= time) {
            if(iFooter != footers.size() - 1) {
                iFooter++;
            } else {
                iFooter = 0;
            }
            lastMillisFooter = time;
        }
        return footers.get(iFooter);
    }

    private String titles() {
        List<String> titles = CC.translate(config.getStringList("SCOREBOARD.TITLE.ANIMATION.ANIMATED"));
        long time = System.currentTimeMillis();
        long interval = TimeUnit.SECONDS.toMillis(config.getInt("SCOREBOARD.TITLE.ANIMATION.INTERVAL"));

        if(lastMillisTitle+ interval <= time) {
            if(iTitle != titles.size() - 1) {
                iTitle++;
            } else {
                iTitle = 0;
            }
            lastMillisTitle = time;
        }
        return titles.get(iTitle);
    }
}
