package me.jesusmx.tacohub.commands.impl;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class TacoHubCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("hubcore.reload")) return false;
        if (args.length == 0) {
            sender.sendMessage(CC.translate("&7&m-------------------------------------------"));
            sender.sendMessage(CC.translate("&6&lTacoHub &7- &fMexican Development"));
            sender.sendMessage(CC.translate("&7"));
            sender.sendMessage(CC.translate("&fAuthor&7: &6JesusMX"));
            sender.sendMessage(CC.translate("&fDiscord&7: &6http://discord.gg/tdtg7ZsjMH"));
            sender.sendMessage(CC.translate("&7"));
            sender.sendMessage(CC.translate("&6&lCommands"));
            sender.sendMessage(CC.translate("&f/setspawn"));
            sender.sendMessage(CC.translate("&f/tacohub reload"));
            sender.sendMessage(CC.translate("&f/builder"));
            sender.sendMessage(CC.translate("&7&m-------------------------------------------"));
            return false;
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                TacoHub.getInstance().getConfigFiles().forEach(c -> {
                    Class<? extends YamlConfiguration> clazz = c.getClass();
                    try {
                        Method method = clazz.getDeclaredMethod("reload");
                        method.setAccessible(true);
                        method.invoke(c);
                        sender.sendMessage(CC.translate("&aSuccessfully reloaded the file " + c.getClass().getSimpleName().replace("File", "").toLowerCase(Locale.ROOT) + ".yml"));
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {}
                });
                sender.sendMessage(CC.translate("&aSuccessfully all TacoHub Files reloaded!"));
            } else {
                sender.sendMessage(CC.translate("&a/tacohub reload"));
            }
        }
        return true;
    }
}
