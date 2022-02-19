package me.jesusmx.tacohub.customtimer.command;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.customtimer.CharProcessor;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.spigotmc.SpigotConfig;

import java.util.concurrent.TimeUnit;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class CustomTimerCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(CC.NO_CONSOLE);
            return false;
        }
        Player player = (Player) sender;
        if(!player.hasPermission("hubcore.customtimer")) {
            try {
                Class.forName("org.spigotmc.SpigotConfig;");
                player.sendMessage(SpigotConfig.unknownCommandMessage);
            } catch (ClassNotFoundException e) {
                return false;
            }
        }

//        /customtimer create <name>                      | /customtimer create sotw
//        /customtimer settext <name>                     | /customtimer settext sotw        &aThe sowt ends in
//        /customtimer setexpire <timezone> <hh:mm:ss>    | /customtimer setexpire EST 12:00:00
//        /customtimer expirein <time>                    | /customtimer expirein 10s
//        /customtimer stop <name>                        | /customtimer stop sotw
//        /customtimer pause <name>                       | /customtimer pause sotw
//        /customtimer remove <name>                      | /customtimer remove sotw
//        /customtimer start <name>                       | /customtimer start sotw

        if(args.length == 2) {
            String id = args[1];
            if(args[0].equalsIgnoreCase("create")) {
                CustomTimer newTimer = new CustomTimer(id);
                CustomTimerCache.getTimers().put(id, newTimer);
                player.sendMessage(CC.GREEN + "Successfully created the customtimer " + args[1]);
            } else if(args[0].equalsIgnoreCase("remove")) {

            } else if(args[0].equalsIgnoreCase("pause")) {
                if(CustomTimerCache.getTimers().containsKey(id)) {
                    CustomTimer timer = CustomTimerCache.getTimers().get(id);
                    timer.setPaused(true);
                    player.sendMessage(CC.GREEN + "Successfully paused!");
                } else {
                    player.sendMessage(CC.RED + "The customtimer " + id + " not exist!");
                    return false;
                }
            } else if(args[0].equalsIgnoreCase("unpase")) {
                if(CustomTimerCache.getTimers().containsKey(id)) {
                    CustomTimer timer = CustomTimerCache.getTimers().get(id);
                    timer.setPaused(false);
                    player.sendMessage(CC.GREEN + "Successfully unpaused!");
                } else {
                    player.sendMessage(CC.RED + "The customtimer " + id + " not exist!");
                    return false;
                }
            } else if(args[0].equalsIgnoreCase("stop")) {

            } else if(args[0].equalsIgnoreCase("settext")) {
                if(CustomTimerCache.getTimers().containsKey(id)) {
                    if(player.hasMetadata("MAKING_TIMER")) {
                        player.sendMessage(CC.RED + "You already waiting to write a text");
                        return false;
                    }
                    CustomTimer timer = CustomTimerCache.getTimers().get(id);
                    player.setMetadata("MAKING_TIMER", new FixedMetadataValue(TacoHub.getInstance(), timer));
                    player.sendMessage(CC.GREEN + "Ready, now write in the chat the text");
                } else {
                    player.sendMessage(CC.RED + "The customtimer " + id + " not exist!");
                    return false;
                }
            } else if(args[0].equalsIgnoreCase("start")) {
                if(CustomTimerCache.getTimers().containsKey(id)) {
                    CustomTimer timer = CustomTimerCache.getTimers().get(id);
                    timer.setActivated(true);
                    player.sendMessage(CC.GREEN + "Successfully stared!");
                } else {
                    player.sendMessage(CC.RED + "The customtimer " + id + " not exist!");
                    return false;
                }
            } else {

            }
        } else if(args.length == 3) {
            String id = args[1];
            if(args[0].equalsIgnoreCase("setexpire")) {

            } else if(args[0].equalsIgnoreCase("expirein")) {
                if(CustomTimerCache.getTimers().containsKey(id)) {
                    CustomTimer timer = CustomTimerCache.getTimers().get(id);
                    timer.setReaming(TimeUnit.SECONDS.toMillis(Long.parseLong(args[2])));
                    player.sendMessage(CC.GREEN + "Successfully set the cooldown to " + args[2]);
                } else {
                    player.sendMessage(CC.RED + "The customtimer " + id + " not exist!");
                    return false;
                }
            } else {

            }
        } else {

        }
        return false;
    }
}
