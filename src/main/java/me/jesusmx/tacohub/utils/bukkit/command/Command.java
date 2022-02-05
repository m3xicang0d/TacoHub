package me.jesusmx.tacohub.utils.bukkit.command;

import org.bukkit.command.CommandSender;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public abstract class Command {

    public abstract boolean execute(CommandSender sender, String label, String[] args);
}
