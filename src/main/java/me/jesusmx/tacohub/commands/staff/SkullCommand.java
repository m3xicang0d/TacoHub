package me.jesusmx.tacohub.commands.staff;

import me.jesusmx.tacohub.utils.bukkit.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You are not a player");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /skull <player>");
            return true;
        }
        player.getInventory().addItem(playerSkullForName(args[0]));
        player.sendMessage(ChatColor.GREEN + "You have received " + args[0] + "'s head.");
        return true;
    }

    private ItemStack playerSkullForName(String name) {
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
        is.setDurability((short)3);
        SkullMeta meta = (SkullMeta)is.getItemMeta();
        meta.setOwner(name);
        is.setItemMeta(meta);
        return is;
    }
}